package com.project.notificationskotlin.helper

import android.app.AlertDialog
import android.content.Context
import java.util.*

fun showAlert(time: Long, title: String, message: String, context: Context) {
    val date = Date(time)
    val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
    val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

    AlertDialog.Builder(context)
        .setTitle("Notification Schedule")
        .setMessage(
            "Title:" + title + "\nMessage:" + message + "\nAt:" + dateFormat.format(date) + " " + timeFormat.format(
                date
            )
        )
        .setPositiveButton("Ok") { _, _ -> }
        .show()

}