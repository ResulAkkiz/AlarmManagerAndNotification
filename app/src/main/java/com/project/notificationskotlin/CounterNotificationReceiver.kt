package com.project.notificationskotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log


class CounterNotificationReceiver:BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent?) {
        Log.e("TAG","onReceive metodu tetiklendi.")
        val service=CounterNotificationService(context)
        service.createNotification(++Counter.value)
    }


}