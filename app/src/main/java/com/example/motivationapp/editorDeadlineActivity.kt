package com.example.motivationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class editorDeadlineActivity : AppCompatActivity() {
    lateinit var date : EditText
    lateinit var subject : EditText
    lateinit var assignment : EditText
    var loc = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor_deadline)
        date = findViewById(R.id.date)
        subject = findViewById(R.id.subject)
        assignment = findViewById(R.id.assignment)
        loc = intent.getIntExtra("Loc", -1)
        date.setText( dueDates[loc] )
        subject.setText( subjects[loc] )
        assignment.setText( assignments[loc] )
    }//onCreate

    fun saveData(view : View) {
        dueDates[loc] = date.text.toString()
        subjects[loc] = subject.text.toString()
        assignments[loc] = assignment.text.toString()
        arrayAdapter.notifyDataSetChanged();

        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.motivationapp", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("AllDueDates", ObjectSerializer.serialize(dueDates)).apply()
        sharedPreferences.edit().putString("AllSubjects", ObjectSerializer.serialize(subjects)).apply()
        sharedPreferences.edit().putString("AllAssignments", ObjectSerializer.serialize(assignments)).apply()

        val intent = Intent(applicationContext, DeadlineActivity::class.java)

        startActivity(intent)

    }//saveData

}//editorDeadlineActivity