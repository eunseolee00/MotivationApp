package com.example.motivationapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class StudyActivity : AppCompatActivity() {

    lateinit var numberPicker : NumberPicker
    lateinit var seconds : TextView
    lateinit var countDownTimer: CountDownTimer
    lateinit var meditationTimer: CountDownTimer
    lateinit var button: Button
    var howLong = 0
    var counterActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        numberPicker = findViewById(R.id.numberPicker)
        seconds = findViewById(R.id.seconds)
        button = findViewById(R.id.start)

        numberPicker.maxValue = 60
        numberPicker.minValue = 0
        numberPicker.value = 25
    }

    fun updateMin(minLeft : Int){
        if(minLeft >= 0){
            numberPicker.value = minLeft
        }
    }

    fun updateSeconds(secondsLeft : Int){
        var sec = secondsLeft - (secondsLeft / 60) * 60
        if(sec >= 0){
            if(sec > 9){
                seconds.text = ( sec ).toString()
            }else{
                seconds.text = "0$sec"
            }

        }

    }

    fun startTimer(view: View){
        if(!counterActive){
            counterActive = true
            button.text = "Stop"

            howLong = numberPicker.value * 60 * 1000 + 100
            countDownTimer = object : CountDownTimer(howLong.toLong(),1000){
                override fun onTick(p0: Long) {
                    updateMin((p0/60000).toInt())
                    updateSeconds((p0/1000).toInt())
                }//onTick

                override fun onFinish() {

                }//onFinish
            }//countDownTimer = object
            countDownTimer.start()

        }//if(!counterActive)
        else{
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                . setTitle("End Timer")
                .setMessage("Do you realy want to stop the timer?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener()
                {
                        dialogInterface: DialogInterface?, i: Int ->
                    countDownTimer.cancel()
                    counterActive = false
                    button.text = "START"

                    numberPicker.value = 25
                    seconds.text = "00"

                })
                .setNegativeButton("NO", null)
                .show()
        }
    }//startTimer

}