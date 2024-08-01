package com.example.firstandroidapp

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF

class Swan(resources: Resources, sizeWH:Int,var x:Float, var y:Float) {
    private var bitmap = BitmapFactory.decodeResource(resources, R.drawable.swan)
    private var bounds = RectF(x,y,x+sizeWH,y+sizeWH)

    init {
        bitmap = Bitmap.createScaledBitmap(bitmap,sizeWH.toInt(),sizeWH.toInt(),true)
    }

    fun drawSwan(canvas: Canvas) {
        canvas.drawBitmap(bitmap, x, y, null)
    }

    fun contains(x: Float,y: Float):Boolean {
       return bounds.contains(x,y)
    }
}