package com.example.colormyviews

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setUpListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint_layout_holder)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpListeners() {
        val listOfViews:List<View> = listOf(binding.boxOneText, binding.boxTwoText,binding.boxThreeText,
            binding.boxFourText, binding.boxFiveText, binding.constraintLayoutHolder
        , binding.redBtn, binding.yellowBtn, binding.blueBtn)
        listOfViews.forEach { it ->
            makeColored(it)
        }
    }

    private fun makeColored(it: View) {
        it.setOnClickListener { it ->
            when(it.id) {
                R.id.box_one_text ->it.setBackgroundColor(Color.RED)
                R.id.box_two_text ->it.setBackgroundColor(Color.YELLOW)
                R.id.box_three_text ->it.setBackgroundColor(Color.GREEN)
                R.id.box_four_text ->it.setBackgroundColor(Color.BLUE)
                R.id.box_five_text ->it.setBackgroundColor(Color.GRAY)
                R.id.red_btn ->  binding.boxThreeText.setBackgroundColor(Color.RED)
                R.id.yellow_btn ->binding.boxFourText.setBackgroundColor(Color.YELLOW)
                R.id.blue_btn ->binding.boxFiveText.setBackgroundColor(Color.BLUE)
                else ->it.setBackgroundColor(Color.CYAN)
            }
        }
    }
}