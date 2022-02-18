package com.example.motivationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }//onCreate

    fun deadlineButton(view: View) {
        val intent = Intent(this, DeadlineActivity::class.java)
        startActivity(intent)
    }

    fun destress(view: View) {
        val intent = Intent(this, DestressActivity::class.java)
        startActivity(intent)
    }

}//MenuActivity