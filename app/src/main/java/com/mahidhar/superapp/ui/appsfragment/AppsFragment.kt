package com.mahidhar.superapp.ui.appsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
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
        val view: View = inflater.inflate(R.layout.fragment_apps, container, false)
//        val financeLayout: LinearLayout = view.findViewById(R.id.apps_finance_layout)
//        val foodLayout: LinearLayout = view.findViewById(R.id.apps_food_layout)
//        val travelLayout: LinearLayout = view.findViewById(R.id.apps_travel_layout)
//        val shoppingLayout: LinearLayout = view.findViewById(R.id.apps_shopping_layout)
//        val healthLayout: LinearLayout = view.findViewById(R.id.apps_health_layout)
//        val dailyLayout: LinearLayout = view.findViewById(R.id.apps_daily_layout)
//        val gamesLayout: LinearLayout = view.findViewById(R.id.apps_games_layout)

        val featuredViewModel: FeaturedViewModel =
            ViewModelProvider(this).get(FeaturedViewModel::class.java)
        featuredViewModel.getMicroAppList().observe(
            viewLifecycleOwner,
            Observer<List<MicroApp>> { microAppList ->
                setCategoryRecyclerView(view.findViewById(R.id.apps_finance_layout),"finance",microAppList)
                setCategoryRecyclerView(view.findViewById(R.id.apps_food_layout),"food",microAppList)
                setCategoryRecyclerView(view.findViewById(R.id.apps_travel_layout),"travel",microAppList)
                setCategoryRecyclerView(view.findViewById(R.id.apps_shopping_layout),"shopping",microAppList)
                setCategoryRecyclerView(view.findViewById(R.id.apps_health_layout),"health",microAppList)
                setCategoryRecyclerView(view.findViewById(R.id.apps_daily_layout),"daily",microAppList)
                setCategoryRecyclerView(view.findViewById(R.id.apps_games_layout),"games",microAppList)

            })

        return view
    }

    fun setCategoryRecyclerView(
        layout: LinearLayout,
        category: String,
        miniAppList: List<MicroApp>
    ) {
        layout.findViewById<TextView>(R.id.category_textview).setText(category.capitalize())
        layout.findViewById<RecyclerView>(R.id.category_recyclerview).adapter =
            FeaturedRecyclerViewAdapter(miniAppList.filter { miniApp: MicroApp -> miniApp.category == category })
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