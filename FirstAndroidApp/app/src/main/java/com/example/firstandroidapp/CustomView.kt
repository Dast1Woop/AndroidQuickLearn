package com.example.firstandroidapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button

class CustomView(c:Context): View(c) {

    private  val paint1 = Paint()
    private  val paint2 = Paint()
    private  var screenW:Float = 0f
    private  var screenH:Float = 0f
    private  var swanList = ArrayList<Swan>()

    init {
        paint1.color = Color.GREEN
        paint1.strokeWidth = 5f

        paint2.color = Color.RED
        paint2.style = Paint.Style.STROKE
        paint2.strokeWidth = 8f
    }

    override fun onDraw(canvas: Canvas) {
        screenW = width.toFloat()
        screenH = height.toFloat()

//         canvas.drawLine(50F,50f,500f,500f,paint1)
//         canvas.drawRect(0.1f*screenW,0.1f*screenH,0.5f*screenW,0.5f*screenH,paint2)

        for (i in 1..4) {
            val swan = Swan(resources,(0.1 * i * screenW).toInt(), (0.1f * screenW), (0.1f * i * screenH))
            swan.drawSwan(canvas)
            swanList.add(swan)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            var action = it.action
            if (action == MotionEvent.ACTION_DOWN) {
                Log.d("on touch", "action_down!")

                for (swan in swanList) {
                    if (swan.contains(it.x, it.y)) {
                        Log.d("swan touched", "swan is clicked!!")
                    }
                }

            }else if(action == MotionEvent.ACTION_UP) {
                Log.d("on touch", "action_up!")
            }
        }

        return true
    }
}