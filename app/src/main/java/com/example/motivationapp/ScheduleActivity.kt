package com.example.motivationapp

import android.app.Dialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.gson.Gson

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

    lateinit var monClass : androidx.constraintlayout.widget.ConstraintLayout
    lateinit var tuesClass : androidx.constraintlayout.widget.ConstraintLayout
    lateinit var wedClass : androidx.constraintlayout.widget.ConstraintLayout
    lateinit var thurClass : androidx.constraintlayout.widget.ConstraintLayout
    lateinit var friClass : androidx.constraintlayout.widget.ConstraintLayout

    lateinit var arrayOfDays : ArrayList<androidx.constraintlayout.widget.ConstraintLayout>

    lateinit var sharedPreferences : SharedPreferences

    lateinit var courseList : ArrayList<course>

    lateinit var addClass : Dialog
    lateinit var addButton: Button
    lateinit var exit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        addClass = Dialog(this)
        addClass.setContentView(R.layout.add_class_popup)

        courseList = ArrayList()
        sharedPreferences = getSharedPreferences("courseSchedule", MODE_PRIVATE)

        exit = addClass.findViewById(R.id.exit)
        addButton = addClass.findViewById(R.id.addClassButton)
        className = addClass.findViewById(R.id.editTextTextPersonName2)
        mon = addClass.findViewById(R.id.checkMon)
        tues = addClass.findViewById(R.id.checkTues)
        wed = addClass.findViewById(R.id.checkWed)
        thur = addClass.findViewById(R.id.checkThur)
        fri = addClass.findViewById(R.id.checkFri)

        monClass = findViewById(R.id.MonClasses)
        tuesClass = findViewById(R.id.TuesClasses)
        wedClass = findViewById(R.id.WedClasses)
        thurClass = findViewById(R.id.ThurClasses)
        friClass = findViewById(R.id.FriClasses)

        arrayOfDays.add(monClass)
        arrayOfDays.add(tuesClass)
        arrayOfDays.add(wedClass)
        arrayOfDays.add(thurClass)
        arrayOfDays.add(friClass)


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

        if(sthPicker.value > ethPicker.value || (sthPicker.value == ethPicker.value
                    && stmPicker.value > etmPicker.value)){
            Toast.makeText(applicationContext,"invalid time!",Toast.LENGTH_LONG).show()
        }else{
            var newCourse = course()

            var nameOfCourse = className.text

            newCourse.name = nameOfCourse.toString()


            newCourse.startHour = sthPicker.value
            newCourse.startMin = stmPicker.value
            newCourse.endHour = ethPicker.value
            newCourse.endMin = etmPicker.value


            if(mon.isChecked){
                newCourse.week[0] = true
            }
            if(tues.isChecked){
                newCourse.week[1] = true
            }
            if(wed.isChecked){
                newCourse.week[2] = true
            }
            if(thur.isChecked){
                newCourse.week[3] = true
            }
            if(fri.isChecked){
                newCourse.week[4] = true
            }

            courseList.add(newCourse)
            saveData()
            Toast.makeText(applicationContext,(courseList.size).toString(),Toast.LENGTH_LONG).show()
        }


    }//addClass

    fun retrieveData(){
        var gson = Gson()
        var json = sharedPreferences.getString("courseList","")
        var arr = gson.fromJson(json,ArrayList::class.java) as ArrayList<course>
        for(c : course in arr){
            for (i in 0..4){
                if (c.week[i]){
                    val lecture = TextView(this )
                    lecture.text = c.name
                    arrayOfDays[i].addView(lecture)
                }
            }
        }
    }

    fun saveData(){

        var gson = Gson()
        var json = gson.toJson(courseList)
        sharedPreferences.edit().putString("courseList",json).apply()
    }



}//AppCompatActivity

