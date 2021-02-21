package com.mahidhar.superapp.ui.homefragment.featured

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mahidhar.superapp.R
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.ui.webapp.WebAppActivity

class FeaturedRecyclerViewAdapter(val microAppList: List<MicroApp>) : RecyclerView.Adapter<FeaturedRecyclerViewAdapter.MicroAppHolder>() {

//    private val micro_app_list:Array<MicroApp> = arrayOf(
//        MicroApp(0,"Pay Bills","Pay Bills", "web","receipt_black","https://www.infosys.com/"),
//        MicroApp(1,"Payment App","Scan & Pay", "web","payments_black","https://www.infosys.com/"),
//        MicroApp(2,"Food Delivery","Order Food", "web","dining_black","https://www.infosys.com/"),
//        MicroApp(3,"Bus Tickets","Book Bus Tickets", "instant","bus_black","https://www.infosys.com/"),
//        MicroApp(4,"Stuff Delivery","Send Stuff", "web","delivery_black","https://www.infosys.com/"),
//        MicroApp(5,"Flight Tickets","Book FLight Tickets", "instant","flight_black","https://www.infosys.com/"),
//        MicroApp(6,"Subscriptions","Subscribe and Pay", "web","subscriptions_black","https://www.infosys.com/"),
//        MicroApp(7,"Take Surveys","Take Surveys", "instant","qewewe","https://www.infosys.com/")
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MicroAppHolder {
        Log.i("Featured Recycler Adapter", microAppList.toString())
        val layout_inflator = LayoutInflater.from(parent.context)
        val view = layout_inflator.inflate(R.layout.main_feature_rv_item, parent, false) ;
        return MicroAppHolder(view);
    }

    override fun onBindViewHolder(holder: MicroAppHolder, position: Int) {
        val micro_app = microAppList[position];
        holder.bind(micro_app)
    }

    override fun getItemCount(): Int {
        return microAppList.size
    }

    class MicroAppHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener{
        var app_view:View = view
        var id:Int?=null
        var type:String?=null
        var description:String?=null
        val title:TextView = view.findViewById<TextView>(R.id.main_feature_app_text)
        val image:ImageView = view.findViewById<ImageView>(R.id.main_feature_app_image)
        var micro_app:MicroApp?=null

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(View.OnLongClickListener {
                showAlertDialog(micro_app)
                Toast.makeText(app_view.context,"Long Tapped on item",Toast.LENGTH_LONG).show()
                true })
        }

        private fun showAlertDialog(micro_app: MicroApp?) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(app_view.context)
            alertDialog.setTitle(micro_app?.name)
            alertDialog.setMessage("Do you want to create a shortcut?")
            alertDialog.setPositiveButton(
                "yes"
            ) { _, _ ->
                Toast.makeText(app_view.context, "Yes,Alert dialog closed.", Toast.LENGTH_LONG).show()
            }
            alertDialog.setNegativeButton(
                "No"
            ) { _, _ ->
                Toast.makeText(app_view.context, "No,Alert dialog closed.", Toast.LENGTH_LONG).show()
            }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

        fun bind(micro_app: MicroApp){
            this.micro_app = micro_app
            this.id = micro_app.id
            this.type = micro_app.type
            this.description = micro_app.description
            this.title.setText(micro_app.description)
            this.image.setImageResource(
                when (micro_app.icon) {
                    "dining_black" -> R.drawable.ic_takeout_dining_black_18dp
                    "subscriptions_black" -> R.drawable.ic_subscriptions_black_18dp
                    "flight_black" -> R.drawable.ic_flight_black_18dp
                    "bus_black" -> R.drawable.ic_directions_bus_black_18dp
                    "receipt_black" -> R.drawable.ic_receipt_black_18dp
                    "payments_black" -> R.drawable.ic_payments_black_18dp
                    "delivery_black" -> R.drawable.ic_delivery_dining_black_18dp
//            "dining_black" -> R.drawable.ic_takeout_dining_black_18dp
                    else -> R.drawable.ic_search_black
                }
            )
        }

        override fun onClick(v: View) {
            Log.d("Featured App", "Clicked")
            val intent = Intent(app_view.context, WebAppActivity::class.java)
            intent.putExtra("source", micro_app?.source)
            intent.putExtra("name", micro_app?.name)
            app_view.context.startActivity(intent)
        }

    }
}