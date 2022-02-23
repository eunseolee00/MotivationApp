package com.example.motivationapp
//Authors: Eunseo (Elsa) Lee and Karen Ren
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }//onCreate

    fun getMotivation(view : View) {
        mediaPlayer = MediaPlayer.create(this, R.raw.bell)
        mediaPlayer.start()

        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }//getMotivation

}//MainActivity