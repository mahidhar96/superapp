package com.mahidhar.superapp.ui.activity.payment

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mahidhar.superapp.MainActivity
import com.mahidhar.superapp.R


class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val textView:TextView = findViewById(R.id.payment_textview)
        val raw_text = intent.getStringExtra("raw").toString()
        textView.setText(raw_text)

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}