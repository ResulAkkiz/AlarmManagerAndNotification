package com.project.notificationskotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class CounterNotificationService(var context: Context) {

    init {
        Log.e("TAG","CounterNotificationService üretildi")
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotificationChannel(
    ) {
        var channel: NotificationChannel? =
            notificationManager.getNotificationChannel(channelId)

        if (channel == null) {
            val channelName = "counter"
            val channelPriority = NotificationManager.IMPORTANCE_DEFAULT
            channel = NotificationChannel(
                channelId,
                channelName,
                channelPriority
            )
            channel.description = "Used for notification counter test"
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(counter: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            101,
            activityIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val broadcastPendingIntent = PendingIntent.getBroadcast(
            context, 102,
            Intent(context, CounterNotificationReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, channelId)
        builder.setContentTitle("Mesaj")
            .setContentText("$counter mesajınız var...")
            .setSmallIcon(R.drawable.notification_ic)
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)
            .addAction(
                R.drawable.notification_ic,
                "Increase",
                broadcastPendingIntent
            )


        notificationManager.notify(1, builder.build())
    }

    companion object {
        const val channelId = "COUNTER_ID"
    }
}