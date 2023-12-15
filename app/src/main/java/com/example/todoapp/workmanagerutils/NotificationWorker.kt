package com.example.todoapp.workmanagerutils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.todoapp.R

class NotificationWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val input = inputData.getString("name")

        sendNotification(context, input)

        return Result.success()
    }

    private  fun sendNotification(context: Context, input: String?) {

        val builder = NotificationCompat.Builder(context, "my_channel")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle("Todo Alert!!")
            .setContentText("Time to do $input")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        val manager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("my_channel", "text", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "This channel send todo scheduling notifications"
            manager.createNotificationChannel(channel)
        }
        manager.notify(1, builder.build())
    }



}
