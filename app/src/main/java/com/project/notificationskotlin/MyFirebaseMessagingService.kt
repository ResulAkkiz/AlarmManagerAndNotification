package com.project.notificationskotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.project.notificationskotlin.CounterNotificationService.Companion.channelId
import com.project.notificationskotlin.helper.Constants.channelName


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
    override fun onMessageReceived(message: RemoteMessage) {
        Log.e("TAG",message.notification?.title!!)
        if (message.notification !=null){
            generateNotification(message.notification?.title!!,message.notification?.body!!)

        }
    }
    private fun generateNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        var builder=NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.notification_ic)
            .setContentTitle(title)
            .setContentText(message)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder=builder.setContent(getRemoteView(title,message))

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel=NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(0,builder.build())


    }

    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView=RemoteViews(channelName,R.layout.notification)
        remoteView.setTextViewText(R.id.titleTextView,title)
        remoteView.setTextViewText(R.id.descTextView,message)
        remoteView.setImageViewResource(R.id.app_logo,R.drawable.phone_ic)

        return remoteView
    }
}