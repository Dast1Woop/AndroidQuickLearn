package com.example.myfirstapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import  okhttp3.Request
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    /***
     * by viewModels(): This is a Kotlin property delegate provided by the AndroidX library. It handles the creation and management of the UserViewModel instance for you.
     */
    private val userViewModel: UserViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val imgV = findViewById<ImageView>(R.id.dic_img)
        val btn = findViewById<Button>(R.id.click_btn)
        btn.text = "click to rqst net and Dice!"

        val viewM = MainPageViewM(this)

        userViewModel.user.observe(this, Observer {
                user:User ->
            println("user:" + user)
        })

        userViewModel.resp4WaitCall.subscribe { item:Resp4WaitCall ->
            println("okhttp Received data: ${item}")
            }

        userViewModel.error.observe(this, Observer { errorMessage:String ->
            // Show error message
            // e.g., showError(errorMessage)
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        btn.setOnClickListener {
//            viewM.rqstNet()

            rqstNet()

            //Random不能加 ()，因为nextInt为 伴生类 方法
            val randomNum = Random.nextInt(6) + 1
//            var imgName:String
//             = when randomNum {
//                1-> imgName = dice_1
//            }

            //when 后面必须有 ()
            val imgID = when(randomNum) {
                1 -> R.drawable.dice_1

                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            imgV.setImageResource(imgID)
        }

    }

    private fun rqstNet() {

        // Trigger network request
//        userViewModel.getUser(1)
        val body =  """
            {
             	"keepIds": ["7423375d889845d4a9d46c2ef2108ae3", "03e823eb14344fa5949dfc7bae942853"]
             }
            """

        userViewModel.getWaitCall(requestBodyString = body)
    }


}