package com.example.motivationapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class editorDeadlineActivity : AppCompatActivity() {
    lateinit var date : EditText
    lateinit var subject : EditText
    lateinit var assignment : EditText
    var loc = -1

    val myCalendar: Calendar = Calendar.getInstance()
    var flag = true

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

        val cdate =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        date.setOnClickListener(View.OnClickListener {
            DatePickerDialog(
                this@editorDeadlineActivity,
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

    fun hide (view : View) {

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (flag) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
        else {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
        }
        flag = !flag
    }//hide

}//editorDeadlineActivity