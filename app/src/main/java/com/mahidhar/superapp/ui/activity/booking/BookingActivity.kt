package com.mahidhar.superapp.ui.activity.booking

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mahidhar.superapp.R
import com.mahidhar.superapp.model.BookingItem
import com.mahidhar.superapp.viewmodel.BookingViewModel

class BookingActivity : AppCompatActivity() {
    var source:String? =null
    var name:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        source = intent.getStringExtra("source").toString()
        name = intent.getStringExtra("name").toString()
        Log.e("name",name.toString())
        Log.e("source",source.toString())
        setTitle(name)
        val bookingRecyclerView: RecyclerView = findViewById(R.id.booking_recyclerview)
        bookingRecyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        val bookingViewModel = ViewModelProvider(this).get(BookingViewModel::class.java)
        bookingViewModel.getBookingItemList().observe(this,
            Observer<List<BookingItem>> { bookingItemList -> bookingRecyclerView.adapter = BookingRecyclerViewAdapter(bookingItemList) })

    }

}