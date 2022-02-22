package com.example.motivationapp

import android.content.DialogInterface
import android.media.MediaPlayer
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

    lateinit var medPicker: NumberPicker
    lateinit var medSeconds : TextView

    lateinit var whiteNoisePlayer : MediaPlayer
    lateinit var meditationPlayer : MediaPlayer

    var howLong = 0
    var medLong = 0
    var counterActive = false
    var medActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        numberPicker = findViewById(R.id.numberPicker)
        seconds = findViewById(R.id.seconds)
        button = findViewById(R.id.start)
        medPicker = findViewById(R.id.numberPicker2)
        medSeconds = findViewById(R.id.seconds2)

        numberPicker.maxValue = 60
        numberPicker.minValue = 0
        numberPicker.value = 25

        medPicker.maxValue = 20
        medPicker.minValue = 0
        medPicker.value = 10

        whiteNoisePlayer = MediaPlayer.create(this,R.raw.whitenoise)
        whiteNoisePlayer.isLooping = true
        meditationPlayer = MediaPlayer.create(this,R.raw.meditation)
        meditationPlayer.isLooping = true
    }//onCreate

    fun updateMedMin(minLeft : Int){
        if(minLeft >= 0){
            medPicker.value = minLeft
        }//if(minLeft >= 0)
    }//updateMedMin

    fun updateMedSeconds(secondsLeft : Int){
        var sec = secondsLeft - (secondsLeft / 60) * 60
        if(sec >= 0){
            if(sec > 9){
                medSeconds.text = ( sec ).toString()
            }else{
                medSeconds.text = "0$sec"
            }//if/else(sec > 9)

        }//if(sec >= 0)

    }//updateMedSeconds

    fun updateMin(minLeft : Int){
        if(minLeft >= 0){
            numberPicker.value = minLeft
        }//if
    }//updateMin

    fun updateSeconds(secondsLeft : Int){
        var sec = secondsLeft - (secondsLeft / 60) * 60
        if(sec >= 0){
            if(sec > 9){
                seconds.text = ( sec ).toString()
            }else{
                seconds.text = "0$sec"
            }//if/else(sec > 9)

        }//if(sec >= 0)

    }//updateSeconds

    fun startTimer(view: View){
        if(!counterActive){
            counterActive = true
            button.text = "Stop"

            whiteNoisePlayer.start()

            howLong = numberPicker.value * 60 * 1000 + 100
            countDownTimer = object : CountDownTimer(howLong.toLong(),1000){
                override fun onTick(p0: Long) {
                    updateMin((p0/60000).toInt())
                    updateSeconds((p0/1000).toInt())
                }//onTick

                override fun onFinish() {

                    whiteNoisePlayer.stop()
                    //counterActive = false
                    medActive = true
                    medLong = medPicker.value * 60 * 1000 + 100
                    meditationPlayer.start()
                    meditationTimer = object : CountDownTimer(medLong.toLong(),1000){
                        override fun onTick(p0: Long) {
                            updateMedMin((p0/60000).toInt())
                            updateMedSeconds((p0/1000).toInt())
                        }//onTick

                        override fun onFinish() {
                            counterActive = false
                            medActive = false
                            button.text = "START"

                            numberPicker.value = 25
                            seconds.text = "00"

                            medPicker.value = 5
                            medSeconds.text = "00"
                            meditationPlayer.stop()

                        }//onFinish()
                    }//meditationTimer = object
                    meditationTimer.start()

                }//onFinish
            }//countDownTimer = object
            countDownTimer.start()

        }//if(!counterActive)
        else{
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                . setTitle("End Timer")
                .setMessage("Do you really want to stop the timer?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener()
                {
                        dialogInterface: DialogInterface?, i: Int ->

                    if(counterActive){
                        countDownTimer.cancel()
                        whiteNoisePlayer.stop()
                        whiteNoisePlayer.prepare()
                    }//if(counterActive)

                    if(medActive){
                        meditationTimer.cancel()
                        meditationPlayer.stop()
                        meditationPlayer.prepare()
                    }//if(medActive)

                    counterActive = false
                    medActive = false
                    button.text = "START"

                    numberPicker.value = 25
                    seconds.text = "00"

                    medPicker.value = 5
                    medSeconds.text = "00"

                })
                .setNegativeButton("NO", null)
                .show()
        }//else
    }//startTimer

}//StudyActivity : AppCompatActivity()