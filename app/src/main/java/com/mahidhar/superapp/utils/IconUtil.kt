package com.mahidhar.superapp.utils

import android.content.Context
import android.content.pm.ShortcutInfo
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

    fun setIconWithURL(context: Context, imageView: ImageView, URL: String) {
        Glide.with(context)
            .asBitmap()
            .load(URL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    imageView.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }

    fun setIconWithURL(context: Context, shoortcutBuilder: ShortcutInfo.Builder, URL: String) {
        Glide.with(context)
            .asBitmap()
            .load(URL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    shoortcutBuilder.setIcon(Icon.createWithBitmap(resource))
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }


}