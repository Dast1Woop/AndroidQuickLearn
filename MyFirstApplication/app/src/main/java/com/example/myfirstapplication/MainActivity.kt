package com.example.myfirstapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import  okhttp3.Request

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.click_btn)
        btn.text = "click to rqst net"

        val viewM = MainPageViewM(this)

        btn.setOnClickListener({
            tv.text = getString(R.string.rqsting)
            viewM.rqstNet()

        })

    }


}