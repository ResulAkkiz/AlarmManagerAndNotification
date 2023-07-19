package com.project.notificationskotlin

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.project.notificationskotlin.helper.Constants.channelID
import com.project.notificationskotlin.helper.Constants.messageExtra
import com.project.notificationskotlin.helper.Constants.titleExtra
import kotlin.random.Random


class ScheduleNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val randomNotifyId = Random.nextInt()

        val notification: Notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.baseline_date_range_24)
            .setContentTitle(intent?.getStringExtra(titleExtra))
            .setContentText(intent?.getStringExtra(messageExtra))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(randomNotifyId, notification)
        // random notify ıd eğer aynı olursa ayrı,iki adet kurulmuş alarm için iki notificationda
        // sırasıyla gösterilir. Fakat en son üretilen notification birinci notificationun üzerine
        // yazılır, ve ekran açıldığında eğer iki bildirimde aktif olduysa sonuncu bildirim sadece
        // gözükür.


    }
}