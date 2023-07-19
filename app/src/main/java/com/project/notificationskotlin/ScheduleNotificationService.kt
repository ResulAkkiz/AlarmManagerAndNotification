package com.project.notificationskotlin

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.project.notificationskotlin.helper.Constants.channelID
import com.project.notificationskotlin.helper.Constants.messageExtra
import com.project.notificationskotlin.helper.Constants.titleExtra
import com.project.notificationskotlin.helper.showAlert
import kotlin.random.Random

class ScheduleNotificationService(var context: Context) {

    fun scheduleNotification(title: String, message: String, time: Long) {
        val randomRequestCode=Random.nextInt()
        val intent = Intent(context, ScheduleNotificationReceiver::class.java)

        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            randomRequestCode,// Birden fazla alarm kurmamızı sağlar, eğer aynı request code ile
            // iki notification yollarsan ilk gönderdiğin notification üzerine yazılır.
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        showAlert(time, title, message, context)
    }

    fun createNotificationChannel() {
        val name = "Schedule Channel"
        val desc = "It's test notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance).apply {
            description = desc
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }


}


