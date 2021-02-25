package com.mahidhar.superapp.ui.activity.booking

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mahidhar.superapp.R
import com.mahidhar.superapp.model.BookingItem
import com.mahidhar.superapp.utils.IconUtil
import kotlin.coroutines.coroutineContext

class BookingRecyclerViewAdapter(val bookingItemList:List<BookingItem>):
    RecyclerView.Adapter<BookingRecyclerViewAdapter.BookingItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingItemHolder {
        Log.i("BookingRecyclerViewAdapter",bookingItemList.toString())
        val layout_inflator = LayoutInflater.from(parent.context)
        val view = layout_inflator.inflate(R.layout.item_booking, parent, false)
        return BookingItemHolder(view)
    }

    override fun onBindViewHolder(holder: BookingItemHolder, position: Int) {
        val bookingItem:BookingItem = bookingItemList[position]
        holder.bind(bookingItem)
    }

    override fun getItemCount(): Int {
        return bookingItemList.size
    }

    class BookingItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val view:View = view
        val image:ImageView = view.findViewById(R.id.item_booking_image)
        val name:TextView = view.findViewById(R.id.item_booking_name)
        val rating:TextView = view.findViewById(R.id.item_booking_rating)
        val additional:TextView = view.findViewById(R.id.item_booking_additional)

        fun bind(bookingItem:BookingItem){
            IconUtil.setIconWithURL(view.context,image,bookingItem.image)
            name.setText(bookingItem.name)
            rating.setText(bookingItem.rating)
            additional.setText(bookingItem.additional)
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

    }
}