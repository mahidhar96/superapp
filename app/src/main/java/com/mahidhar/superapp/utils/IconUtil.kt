package com.mahidhar.superapp.utils

import com.mahidhar.superapp.R

object IconUtil {
    fun getIcon(iconId:String):Int{
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
}