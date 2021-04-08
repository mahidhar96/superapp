package com.mahidhar.superapp.ui.activity.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mahidhar.superapp.R

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val textView:TextView = findViewById(R.id.payment_textview)
        val raw_text = intent.getStringExtra("raw").toString()
        textView.setText(raw_text)

    }
}