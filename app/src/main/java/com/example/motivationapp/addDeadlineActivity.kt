package com.example.motivationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class addDeadlineActivity : AppCompatActivity() {
    lateinit var date : EditText
    lateinit var subject : EditText
    lateinit var assignment : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_deadline)
        date = findViewById(R.id.date)
        subject = findViewById(R.id.subject)
        assignment = findViewById(R.id.assignment)
    }//onCreate

    fun saveData(view : View) {

        dueDates.add(date.text.toString())
        subjects.add(subject.text.toString())
        assignments.add(assignment.text.toString())
        //arrayAdapter.notifyDataSetChanged();

        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.motivationapp", Context.MODE_PRIVATE)

        sharedPreferences.edit().putString("AllDueDates", ObjectSerializer.serialize(dueDates)).apply()
        sharedPreferences.edit().putString("AllSubjects", ObjectSerializer.serialize(subjects)).apply()
        sharedPreferences.edit().putString("AllAssignments", ObjectSerializer.serialize(assignments)).apply()

        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)

    }//saveData

}//addDeadlineActivity