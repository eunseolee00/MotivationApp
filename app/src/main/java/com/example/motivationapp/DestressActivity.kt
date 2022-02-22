package com.example.motivationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation

class DestressActivity : AppCompatActivity() {

    lateinit var dumpling : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destress)
        dumpling = findViewById(R.id.dumpling)

    }//onCreate

    fun punch (view : View) {

        dumpling.animate().translationYBy(-2000f).rotation(720f).setDuration(2000)

        val handler = Handler()
        handler.postDelayed(Runnable {
            dumpling.animate().translationX(0f).translationY(0f).rotation(720f)
        }, 2500)

        dumpling.clearAnimation()
    }//punch


}//DestressActivity