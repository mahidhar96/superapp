package com.mahidhar.superapp.ui.activity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mahidhar.superapp.R

class WebAppActivity : AppCompatActivity() {
    var source:String? =null
    var name:String?=null
    private lateinit var webApp:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_app)
        source = intent.getStringExtra("source").toString()
        name = intent.getStringExtra("name").toString()
        Log.e("name",name.toString())
        Log.e("source",source.toString())
        setTitle(name)
        val webApp = findViewById<WebView>(R.id.webapp_webview)
        webApp.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
//                binding.mainRefreshLayout.isRefreshing = true
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                binding.mainRefreshLayout.isRefreshing = false
            }
        }

        webApp.requestFocus()
        webApp.getSettings().lightTouchEnabled = true
        webApp.settings.javaScriptEnabled = true
        webApp.settings.domStorageEnabled = true
        webApp.settings.useWideViewPort = true
        webApp.settings.loadWithOverviewMode = true
        webApp.settings.setGeolocationEnabled(true)
        webApp.webChromeClient= object:WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress<100){

                }
            }

            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback
            ) {
                callback.invoke(origin, true, false)
            }
        }
        webApp.loadUrl(source!!)

    }

    override fun onBackPressed() {
        return if(this::webApp.isInitialized && webApp.canGoBack()) {
            webApp.goBack()
            webApp.canGoBack()
            Unit
        } else
            super.onBackPressed()

    }
}