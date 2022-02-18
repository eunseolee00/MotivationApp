package com.example.motivationapp
//Authors: Eunseo (Elsa) Lee and Karen Ren
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }//onCreate

    fun getMotivation(view : View) {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }//getMotivation

}//MainActivity