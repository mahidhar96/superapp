package com.mahidhar.superapp.ui.homefragment.featured

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.browser.trusted.TrustedWebActivityIntentBuilder
import androidx.recyclerview.widget.RecyclerView
import com.google.androidbrowserhelper.trusted.TwaLauncher
import com.mahidhar.superapp.R
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.ui.activity.WebAppActivity
import com.mahidhar.superapp.ui.activity.booking.BookingActivity
import com.mahidhar.superapp.utils.IconUtil
import com.mahidhar.superapp.utils.ShortcutUtil


class FeaturedRecyclerViewAdapter(val microAppList: List<MicroApp>) :
    RecyclerView.Adapter<FeaturedRecyclerViewAdapter.MicroAppHolder>() {

//    private val micro_app_list:Array<MicroApp> = arrayOf(
//        MicroApp(0,"Pay Bills","Pay Bills", "web","receipt_black","https://127.0.0.1:1001"),
//        MicroApp(1,"Payment App","Scan & Pay", "web","payments_black","https://127.0.0.1:1001"),
//        MicroApp(2,"Food Delivery","Order Food", "web","dining_black","https:/127.0.0.1:1001"),
//        MicroApp(3,"Bus Tickets","Book Bus Tickets", "instant","bus_black","https://127.0.0.1:1001"),
//        MicroApp(4,"Stuff Delivery","Send Stuff", "web","delivery_black","https://127.0.0.1:1001"),
//        MicroApp(5,"Flight Tickets","Book FLight Tickets", "instant","flight_black","https://127.0.0.1:1001"),
//        MicroApp(6,"Subscriptions","Subscribe and Pay", "web","subscriptions_black","https://127.0.0.1:1001"),
//        MicroApp(7,"Take Surveys","Take Surveys", "instant","qewewe","https://127.0.0.1:1001")
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MicroAppHolder {
        Log.i("Featured Recycler Adapter", microAppList.toString())
        val layout_inflator = LayoutInflater.from(parent.context)
        val view = layout_inflator.inflate(R.layout.home_feature_rv_item, parent, false);
        return MicroAppHolder(view);
    }

    override fun onBindViewHolder(holder: MicroAppHolder, position: Int) {
        val micro_app = microAppList[position];
        holder.bind(micro_app)
    }

    override fun getItemCount(): Int {
        return microAppList.size
    }

    class MicroAppHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var app_view: View = view
        var id: Int? = null
        var type: String? = null
        var description: String? = null
        val title: TextView = view.findViewById<TextView>(R.id.main_feature_app_text)
        val image: ImageView = view.findViewById<ImageView>(R.id.main_feature_app_image)
        var micro_app: MicroApp? = null

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(View.OnLongClickListener {
                showAlertDialog(micro_app)
                true
            })
        }

        private fun showAlertDialog(micro_app: MicroApp?) {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(app_view.context)
            alertDialog.setTitle(micro_app?.name)
            alertDialog.setMessage("Do you want to create a shortcut?")
            alertDialog.setPositiveButton(
                "yes"
            ) { _, _ ->
                ShortcutUtil.createWebActivityShortcut(app_view.context, micro_app!!)
                Toast.makeText(app_view.context, "Added Shortcut to Home Screen", Toast.LENGTH_LONG)
                    .show()
            }
            alertDialog.setNegativeButton(
                "No"
            ) { _, _ ->
                Toast.makeText(app_view.context, "Shortcut Not Added", Toast.LENGTH_LONG).show()
            }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

        fun bind(micro_app: MicroApp) {
            this.micro_app = micro_app
            this.id = micro_app.id
            this.type = micro_app.type
            this.description = micro_app.description
            this.title.setText(micro_app.name)
            IconUtil.setIconWithURL(app_view.context, image, micro_app.icon)
//            this.image.setImageResource(IconUtil.getIcon(micro_app.icon))
        }

        override fun onClick(v: View) {
            Log.d("Featured App", "Clicked")
            var intent: Intent? = null
            when (micro_app!!.type) {
                "booking" -> {
                    intent = Intent(app_view.context, BookingActivity::class.java)
                    intent.putExtra("source", micro_app?.source)
                    intent.putExtra("name", micro_app?.name)
                }
                //web
                else -> {
                    intent = Intent(app_view.context, WebAppActivity::class.java)
                    intent.putExtra("source", micro_app?.source)
                    intent.putExtra("name", micro_app?.name)
                }
            }
            app_view.context.startActivity(intent)

//            launchTwa(Uri.parse(micro_app?.source),app_view.context)
        }

        fun launchTwa(uri: Uri, context: Context) {
            val builder = TrustedWebActivityIntentBuilder(uri)
                .setNavigationBarColor(Color.RED) // Use the builder to customise.
                .setToolbarColor(Color.BLUE)
//            TwaLauncher(builder,description,id)
            val launcher = TwaLauncher(context)
            //launcher.launch(builder, null, null)
            launcher.launch(uri)
        }

    }
}