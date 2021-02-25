package com.mahidhar.superapp.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.ui.activity.WebAppActivity
import com.mahidhar.superapp.ui.activity.booking.BookingActivity

object ShortcutUtil {
    fun createWebActivityShortcut(context:Context,microApp:MicroApp) {
        Log.i("FeaturedRecyclerViewAdapter", "createShortcutApp")

        val shortcutManager = ContextCompat.getSystemService<ShortcutManager>(
            context,
            ShortcutManager::class.java
        )

        if (shortcutManager!!.isRequestPinShortcutSupported) {
            // Assumes there's already a shortcut with the ID "my-shortcut".
            // The shortcut must be enabled.
            val pinShortcutInfoBuilder: ShortcutInfo.Builder = ShortcutInfo.Builder(context, "superapp_"+microApp.name)
            pinShortcutInfoBuilder.setShortLabel(microApp.name)
            var intent: Intent? = null
            when (microApp.type) {
                "booking" -> {
                    intent = Intent(Intent.ACTION_VIEW, null, context, BookingActivity::class.java)
                    intent.putExtra("source", microApp.source)
                    intent.putExtra("name", microApp.name)
                }
                //web
                else -> {
                    intent = Intent(Intent.ACTION_VIEW, null, context, WebAppActivity::class.java)
                    intent.putExtra("source", microApp.source)
                    intent.putExtra("name", microApp.name)
                }
            }
            pinShortcutInfoBuilder.setIntent(intent)
            IconUtil.setIconWithURL(context,pinShortcutInfoBuilder,microApp.icon)
//            pinShortcutInfoBuilder.setIcon(Icon.createWithResource(context, icon))
            val pinShortcutInfo = pinShortcutInfoBuilder.build()

            // Create the PendingIntent object only if your app needs to be notified
            // that the user allowed the shortcut to be pinned. Note that, if the
            // pinning operation fails, your app isn't notified. We assume here that the
            // app has implemented a method called createShortcutResultIntent() that
            // returns a broadcast intent.
            val pinnedShortcutCallbackIntent = shortcutManager.createShortcutResultIntent(
                pinShortcutInfo
            )

            // Configure the intent so that your app's broadcast receiver gets
            // the callback successfully.For details, see PendingIntent.getBroadcast().
            val successCallback = PendingIntent.getBroadcast(
                context, /* request code */ 0,
                pinnedShortcutCallbackIntent, /* flags */ 0
            )

            shortcutManager.requestPinShortcut(
                pinShortcutInfo,
                successCallback.intentSender
            )
        }
    }
}