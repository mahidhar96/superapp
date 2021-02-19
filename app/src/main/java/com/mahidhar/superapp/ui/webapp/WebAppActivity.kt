package com.mahidhar.superapp.ui.webapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import com.mahidhar.superapp.R

class WebAppActivity : AppCompatActivity() {
    var source:String? =null
    var name:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_app)
        source = intent.getStringExtra("source").toString()
        name = intent.getStringExtra("name").toString()
        Log.e("name",name.toString())
        Log.e("source",source.toString())
        setTitle(name)
        val web_app = findViewById<WebView>(R.id.webapp_webview)
        web_app.loadUrl(source!!)
    }
}