package com.example.motivationapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.NumberPicker

class ScheduleActivity : AppCompatActivity() {

    lateinit var layout : androidx.constraintlayout.widget.ConstraintLayout
    lateinit var sthPicker : NumberPicker
    lateinit var stmPicker : NumberPicker
    lateinit var ethPicker: NumberPicker
    lateinit var etmPicker: NumberPicker
    lateinit var className : EditText
    lateinit var mon : CheckBox
    lateinit var tues : CheckBox
    lateinit var wed : CheckBox
    lateinit var thur : CheckBox
    lateinit var fri : CheckBox

    lateinit var courseListMon : HashMap<String, String>
    lateinit var courseListTue : HashMap<String, String>
    lateinit var courseListWed : HashMap<String, String>
    lateinit var courseListThur : HashMap<String, String>
    lateinit var courseListFri : HashMap<String, String>
    

    lateinit var addClass : Dialog
    lateinit var addButton: Button
    lateinit var exit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        addClass = Dialog(this)
        addClass.setContentView(R.layout.add_class_popup)


        exit = addClass.findViewById(R.id.exit)
        addButton = addClass.findViewById(R.id.addClass)

        sthPicker = addClass.findViewById(R.id.startTimeHourPicker)
        sthPicker.maxValue = 19
        sthPicker.minValue = 8
        sthPicker.value = 10

        stmPicker = addClass.findViewById(R.id.startTimeMinPicker)
        stmPicker.maxValue = 59
        stmPicker.minValue = 0
        stmPicker.value = 0

        ethPicker = addClass.findViewById(R.id.endTimerHourPicker)
        ethPicker.maxValue = 20
        ethPicker.minValue = 8
        ethPicker.value = 12

        etmPicker = addClass.findViewById(R.id.endTimerMinPicker)
        etmPicker.maxValue = 59
        etmPicker.minValue = 0
        etmPicker.value = 0


        exit = addClass.findViewById(R.id.exit)
        exit.setOnClickListener {
            addClass.dismiss()
        }


        layout = findViewById(R.id.layout)
        layout.setOnClickListener{
            addClass.show()
        }

        addButton.setOnClickListener{
            addClass()
        }




    }//onCreate

    fun addClass(){

    }//addClass

}//AppCompatActivity

