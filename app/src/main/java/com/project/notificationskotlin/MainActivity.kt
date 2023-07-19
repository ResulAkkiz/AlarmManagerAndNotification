package com.project.notificationskotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.notificationskotlin.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val service = ScheduleNotificationService(this)
        service.createNotificationChannel()
        binding.submitButton.setOnClickListener {

            val title=binding.titleEditText.text.toString()
            val message=binding.messageEditText.text.toString()

            service.scheduleNotification(title,message,getTime())
        }

        /*        Basic Counter local Notification
        val service = CounterNotificationService(this)
        service.createNotificationChannel()
        findViewById<Button>(R.id.notificationButton1).setOnClickListener {
            service.createNotification(Counter.value)
        }*/

    }

    private fun getTime(): Long {
        val minute = binding.timePicker.minute
        val hour = binding.timePicker.hour
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        Log.e("TAG",calendar.timeInMillis.toString())

        return calendar.timeInMillis

    }


}