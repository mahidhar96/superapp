package com.mahidhar.superapp.ui.appsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mahidhar.superapp.R
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.ui.homefragment.featured.FeaturedRecyclerViewAdapter
import com.mahidhar.superapp.viewmodel.FeaturedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AppsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_apps, container, false)
        val financeRecyclerView:RecyclerView = view.findViewById(R.id.apps_finance_recycler_view)
        val storesRecyclerView:RecyclerView = view.findViewById(R.id.apps_stores_recycler_view)

        val featuredViewModel:FeaturedViewModel = ViewModelProvider(this).get(FeaturedViewModel::class.java)
        featuredViewModel.getMicroAppList().observe(
            viewLifecycleOwner,
            Observer<List<MicroApp>> { microAppList ->
                financeRecyclerView?.adapter = FeaturedRecyclerViewAdapter(microAppList)
                storesRecyclerView?.adapter = FeaturedRecyclerViewAdapter(microAppList)
            })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AppsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AppsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}