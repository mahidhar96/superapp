package com.mahidhar.superapp.ui.homefragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mahidhar.superapp.R
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.ui.homefragment.featured.FeaturedRecyclerViewAdapter
import com.mahidhar.superapp.ui.homefragment.sponsors.SponsorholderFragment
import com.mahidhar.superapp.ui.webapp.WebAppActivity
import com.mahidhar.superapp.viewmodel.FeaturedViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var featuredViewModel: FeaturedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)

        val homeSponsorPager = view.findViewById<ViewPager2>(R.id.home_sponsor_pager)
        val homeSponsorTablayout = view.findViewById<TabLayout>(R.id.home_sponsor_tablayout)
        val homeFeaturedRecyclerView = view.findViewById<RecyclerView>(R.id.home_featured_recyclerview)

        val adapter = ScreenSlidePagerAdapter(activity)
        homeSponsorPager?.adapter = adapter
        TabLayoutMediator(homeSponsorTablayout, homeSponsorPager) { tab, position ->

        }.attach()

        featuredViewModel = ViewModelProvider(this).get(FeaturedViewModel::class.java)
        featuredViewModel.getMicroAppList().observe(
            viewLifecycleOwner,
            Observer<List<MicroApp>> { microAppList ->
                homeFeaturedRecyclerView?.adapter = FeaturedRecyclerViewAdapter(microAppList)
            })
        return view
    }

    internal class ScreenSlidePagerAdapter(fa: FragmentActivity?) : FragmentStateAdapter(fa!!) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment =
            SponsorholderFragment.newInstance(
                position
            )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}