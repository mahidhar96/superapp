package com.mahidhar.superapp.utils

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.util.Log
import androidx.core.content.ContextCompat
import com.mahidhar.superapp.R
import com.mahidhar.superapp.ui.webapp.WebAppActivity

object ShortcutUtil {
    fun createWebActivityShortcut(context:Context,name:String,source:String,icon:Int) {
        Log.i("FeaturedRecyclerViewAdapter", "createShortcutApp")

        val shortcutManager = ContextCompat.getSystemService<ShortcutManager>(
            context,
            ShortcutManager::class.java
        )

        if (shortcutManager!!.isRequestPinShortcutSupported) {
            // Assumes there's already a shortcut with the ID "my-shortcut".
            // The shortcut must be enabled.
            val pinShortcutInfoBuilder = ShortcutInfo.Builder(context, "superapp_"+name)
            pinShortcutInfoBuilder.setShortLabel(name)
            val intent = Intent(Intent.ACTION_VIEW, null, context, WebAppActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("source",source)
            pinShortcutInfoBuilder.setIntent(intent)
            pinShortcutInfoBuilder.setIcon(Icon.createWithResource(context, icon))
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