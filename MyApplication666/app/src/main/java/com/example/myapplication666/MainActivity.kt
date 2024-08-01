package com.example.myapplication666

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random
import kotlin.time.Duration

private const val btnText = "Let`s roll"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val btn = findViewById<Button>(R.id.roll_btn)
        btn.text = btnText

        btn.setOnClickListener({
//            Toast.makeText(this,"btn clicked", Toast.LENGTH_SHORT).show()
            diceNow()
        })
    }

    private fun diceNow() {
        val tv = findViewById<TextView>(R.id.rslt_text)
        val num = Random.nextInt(6) + 1
        tv.text = num.toString()
    }
}