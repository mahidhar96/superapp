package com.mahidhar.superapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mahidhar.superapp.ui.activity.qr.QRActivity
import com.mahidhar.superapp.ui.appsfragment.AppsFragment
import com.mahidhar.superapp.ui.historyfragment.HistoryFragment
import com.mahidhar.superapp.ui.homefragment.HomeFragment

class MainActivity : AppCompatActivity() {
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val main_viewpager: ViewPager = findViewById<ViewPager>(R.id.main_viewpager)
        val main_tablayout: TabLayout = findViewById<TabLayout>(R.id.main_tablayout)
        val main_qr: ImageView = findViewById(R.id.main_qr)
        setupViewPager(main_viewpager)
        main_tablayout.setupWithViewPager(main_viewpager)
        main_tablayout.getTabAt(0)!!.setIcon(R.drawable.ic_home_black_18dp)
        main_tablayout.getTabAt(1)!!.setIcon(R.drawable.ic_apps_black_18dp)
        main_tablayout.getTabAt(2)!!.setIcon(R.drawable.ic_history_black_18dp)
        main_qr.setOnClickListener(View.OnClickListener { view ->
            view.context.startActivity(
                Intent(
                    view.context,
                    QRActivity::class.java
                )
            )
        })


        supportActionBar?.hide()

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

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(AppsFragment(), "Apps")
        adapter.addFragment(HistoryFragment(), "History")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

}