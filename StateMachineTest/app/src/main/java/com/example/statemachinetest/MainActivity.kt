package com.example.statemachinetest;
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.statemachinetest.MyStateMachine
import com.example.statemachinetest.R
import com.example.statemachinetest.WaterEvent


//import android.app.Activity;

class MainActivity : AppCompatActivity() {
    lateinit var sm: MyStateMachine
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        handler = Handler(Looper.getMainLooper())
        testStateMachine()

    }

    private fun testStateMachine() {
        sm = MyStateMachine()

        Log.d("MyStateMachine", "onCreate: ${sm.stateM.state}")

        val run1 = Runnable {
            sm.stateM.transition(WaterEvent.RongHua)
            Log.d("MyStateMachine", "onCreate: ${sm.stateM.state}")
        }
        handler.postDelayed(run1,2000)

        handler.postDelayed({

            sm.stateM.transition(WaterEvent.QiHua)
            Log.d("MyStateMachine", "onCreate: ${sm.stateM.state}")
            sm.stateM.transition(WaterEvent.YeHua)
            Log.d("MyStateMachine", "onCreate: ${sm.stateM.state}")

        },5000)


    }


}