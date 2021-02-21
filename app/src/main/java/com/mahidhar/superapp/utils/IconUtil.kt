package com.mahidhar.superapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import coil.Coil
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.mahidhar.superapp.R


object IconUtil {
    fun getIcon(iconId: String): Int {
        val icon = when (iconId) {
            "dining_black" -> R.mipmap.ic_takeout_dining_color
            "subscriptions_black" -> R.mipmap.ic_subscriptions_color
            "flight_black" -> R.mipmap.ic_flight_color
            "bus_black" -> R.mipmap.ic_bus_color
            "receipt_black" -> R.mipmap.ic_receipt_color
            "payments_black" -> R.mipmap.ic_payments_color
            "delivery_black" -> R.mipmap.ic_takeout_dining_color
//            "dining_black" -> R.drawable.ic_takeout_dining_black_18dp
            else -> R.drawable.ic_search_color
        }
        return icon
    }

    fun getIconBlack(iconId: String): Int {
        val icon = when (iconId) {
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
        return icon
    }

    fun getImage(context:Context,url:String): Drawable? {
        var image:Drawable?=null
        val imageLoader = ImageLoader.Builder(context).availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
        val request = ImageRequest.Builder(context)
            .data(url)
            .target(
                onStart = { placeholder ->
                    if (placeholder != null) {
                        image = placeholder
                    }// Handle the placeholder drawable.
                },
                onSuccess = { result ->
                    image = result// Handle the successful result.
                },
                onError = { error ->
                    error// Handle the error drawable.
                }
            )
            .build()
        imageLoader.enqueue(request)
        return image
    }

}