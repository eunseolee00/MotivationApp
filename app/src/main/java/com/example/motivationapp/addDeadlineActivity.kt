package com.example.motivationapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import java.util.*
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener

import android.widget.DatePicker
import java.text.SimpleDateFormat


class addDeadlineActivity : AppCompatActivity() {
    lateinit var date : EditText
    lateinit var subject : EditText
    lateinit var assignment : EditText
    val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_deadline)

        date = findViewById(R.id.date)
        subject = findViewById(R.id.subject)
        assignment = findViewById(R.id.assignment)

        val cdate =
            OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        date.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                this@addDeadlineActivity,
                cdate,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        })

    }//onCreate

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        date.setText(dateFormat.format(myCalendar.time))
    }

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

        val intent = Intent(applicationContext, DeadlineActivity::class.java)
        startActivity(intent)

    }//saveData

}//addDeadlineActivity