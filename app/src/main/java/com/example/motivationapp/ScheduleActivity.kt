package com.example.motivationapp

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    lateinit var monClass : RelativeLayout
    lateinit var tuesClass : RelativeLayout
    lateinit var wedClass : RelativeLayout
    lateinit var thurClass : RelativeLayout
    lateinit var friClass : RelativeLayout

    lateinit var arrayOfDays : ArrayList<RelativeLayout>

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
        arrayOfDays = ArrayList()

        sharedPreferences = applicationContext.getSharedPreferences("courseSchedule", Context.MODE_PRIVATE)

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
        }//exit.setOnClickListener

        layout = findViewById(R.id.layout)
        layout.setOnClickListener{
            addClass.show()
        }//layout.setOnClickListener

        addButton.setOnClickListener{
            addClass()
        }//addButton.setOnClickListener

        retrieveData()

    }//onCreate

    //https://stackoverflow.com/questions/45411634/set-runtime-margin-to-any-view-using-kotlin
    fun TextView.setMargins(
        left: Int = this.marginLeft,
        top: Int = this.marginTop,
        right: Int = this.marginRight,
        bottom: Int = this.marginBottom,
    ) {
        layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
            setMargins(left, top, right, bottom)
        }//layoutParams = (layoutParams
    }//TextView.setMargins


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
        }


    }//addClass

    fun retrieveData() {

        sharedPreferences =
            applicationContext.getSharedPreferences("courseSchedule", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("courseList", "")
        val tptk = object : TypeToken<ArrayList<course>>() {}
        val arr = gson.fromJson<ArrayList<course>>(json, tptk.type)

        if (arr.isNotEmpty()) {
            courseList = arr
        }//if (arr.isNotEmpty())


        /*
        arr = ObjectSerializer.
            deserialize(
                    sharedPreferences.getString("courseList",ObjectSerializer.serialize(ArrayList<course>()))
                )as ArrayList<course>

         */
        //Toast.makeText(applicationContext,(arr[0].name).toString(),Toast.LENGTH_LONG).show()


        for (c: course in courseList) {

            for (i in 0..4) {
                if (c.week[i]) {
                    val lecture = TextView(this)
                    var stm = String()
                    var edm = String()
                    if (c.startMin < 10) {
                        stm = "0" + c.startMin
                    }
                    if (c.endMin < 10) {
                        edm = "0" + c.endMin
                    }
                    lecture.text = c.name + "\n" + c.startHour.toString() + ":" +
                            stm + "-" +
                            c.endHour.toString() + ":" + edm

                    lecture.textSize = 12f

                    lecture.setBackgroundColor((Color.LTGRAY))
                    arrayOfDays[i].addView(lecture)

                    lecture.setOnLongClickListener{
                        AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            . setTitle("Delete course")
                            .setMessage("Do you really want to delete this course?")
                            .setPositiveButton("Yes", DialogInterface.OnClickListener()
                            {
                                    dialogInterface: DialogInterface?, i: Int ->
                                courseList.remove(c)
                                saveData()
                            })
                            .setNegativeButton("NO", null)
                            .show()
                        return@setOnLongClickListener true
                    }//lecture.setOnLongClickListener
                    //test

                    //val p = lecture.layoutParams as ViewGroup.MarginLayoutParams
                    //p.setMargins(0,50,0,0)


                    //val p = lecture.layoutParams as RelativeLayout.LayoutParams
                    //p.topMargin = 50
                    //lecture.layoutParams = p

                    val marginToTop = 20 + ((c.startHour - 8) * 60 + (c.startMin)) / 60 * 130
                    var timeSpanInMin = (c.endHour - c.startHour) * 60 + c.endMin - c.startMin

                    lecture.setMargins(0, marginToTop, 0, 0)
                    lecture.height = timeSpanInMin / 60 * 130
                }//if (c.week[i])


            }//for (i in 0..4)
        }//for(c : course in arr)
    }//retrieveData()

    fun saveData() {

        val gson = Gson()
        val json = gson.toJson(courseList)
        sharedPreferences.edit().putString("courseList", json).commit()
    }//saveData()



}//AppCompatActivity

