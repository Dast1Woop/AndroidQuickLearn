package com.example.myfirstapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import  okhttp3.Request

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

        val tv = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.click_btn)
        btn.text = "click to rqst net"

        val viewM = MainPageViewM(this)

        userViewModel.user.observe(this, Observer {
                user:User ->
            println("user:" + user)
        })

        userViewModel.resp4WaitCall.observe(this, Observer {
                resp4WaitCall:Resp4WaitCall ->
            println("okhttp resp4WaitCall:" + resp4WaitCall)
        })

        userViewModel.error.observe(this, Observer { errorMessage:String ->
            // Show error message
            // e.g., showError(errorMessage)
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        btn.setOnClickListener({
            tv.text = getString(R.string.rqsting)
//            viewM.rqstNet()

            rqstNet()
        })

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