package com.mahidhar.superapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.ui.main.featured.FeaturedRecyclerViewAdapter
import com.mahidhar.superapp.ui.main.sponsors.SponsorholderFragment
import com.mahidhar.superapp.viewmodel.FeaturedViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var featuredViewModel: FeaturedViewModel
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainSponsorPager = findViewById<ViewPager2>(R.id.main_sponsor_pager)
        val mainSponsorTablayout = findViewById<TabLayout>(R.id.main_sponsor_tablayout)
        val mainFeaturedRecyclerView = findViewById<RecyclerView>(R.id.main_featured_recyclerview)

        val adapter = ScreenSlidePagerAdapter(this)
        mainSponsorPager.adapter = adapter
        TabLayoutMediator(mainSponsorTablayout, mainSponsorPager) { tab, position ->

        }.attach()

        supportActionBar?.hide()

        featuredViewModel = ViewModelProvider(this).get(FeaturedViewModel::class.java)
        featuredViewModel.getMicroAppList().observe(this, Observer<List<MicroApp>> { microAppList ->
            mainFeaturedRecyclerView.adapter = FeaturedRecyclerViewAdapter(microAppList)
        })

    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        } else {
            Log.d("Location", "getLocation: permissions granted")
        }
    }

}

class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        SponsorholderFragment.newInstance(
            position
        )
}