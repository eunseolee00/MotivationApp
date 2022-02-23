package com.example.motivationapp

import android.media.MediaPlayer
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
    lateinit var punchPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destress)
        dumpling = findViewById(R.id.dumpling)

    }//onCreate

    fun punch (view : View) {

        dumpling.animate().translationYBy(-2000f).rotationBy(720f).setDuration(2000)

        punchPlayer = MediaPlayer.create(this, R.raw.punching)
        punchPlayer.start()

        val handler = Handler()
        handler.postDelayed(Runnable {
            dumpling.animate().translationX(0f).translationY(0f)
        }, 1500)

    }//punch


}//DestressActivity