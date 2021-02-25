package com.mahidhar.superapp.ui.activity.booking

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.mahidhar.superapp.R
import com.mahidhar.superapp.utils.IconUtil

class BookingItemActivity : AppCompatActivity() {
    var name: String? = null
    var image: String? = null
    var type: String? = null
    var additional: String? = null
    var rating: String? = null
    var bookingInput_1: TextView? = null
    var bookingInput_2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_item)
        name = intent.getStringExtra("name").toString()
        image = intent.getStringExtra("image").toString()
        type = intent.getStringExtra("type").toString()
        additional = intent.getStringExtra("additional").toString()
        rating = intent.getStringExtra("rating").toString()

        val bookingImageView = findViewById<ImageView>(R.id.booking_item_image)
        IconUtil.setIconWithURL(this, bookingImageView, image.toString())
        val bookingName = findViewById<TextView>(R.id.booking_item_name)
        val bookingAdditional = findViewById<TextView>(R.id.booking_item_additional)
        val bookingRating = findViewById<TextView>(R.id.booking_item_rating)
        val bookingString_1 = findViewById<TextView>(R.id.booking_item_string_1)
        bookingInput_1 = findViewById<TextView>(R.id.booking_item_input_1)
        val bookingString_2 = findViewById<TextView>(R.id.booking_item_string_2)
        bookingInput_2 = findViewById<TextView>(R.id.booking_item_input_2)
//        val bookingButton = findViewById<ImageView>(R.id.booking_item_button)

        bookingName.setText(name)
        bookingAdditional.setText(additional)
        bookingRating.setText(rating)
        bookingString_1.setText("Book a table for:")
        bookingString_2.setText("At time:")


//        bookingButton.bringToFront()
//        bookingButton?.setOnClickListener{view->
//            Toast.makeText(this,"sdsdsdsdsdsdsd",Toast.LENGTH_SHORT)
//            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(view.context)
//            alertDialog.setTitle("Confirmation")
//            alertDialog.setMessage(
//                "Booked a table for " + bookingInput_1.toString() +
//                        "At time: " + bookingInput_2.toString()
//            )
//            alertDialog.setPositiveButton("Ok!") { _, _ -> finish() }

        }


    fun bookingButtonClicked(view:View){
        Toast.makeText(view.context,"Working",Toast.LENGTH_SHORT)
    }

}