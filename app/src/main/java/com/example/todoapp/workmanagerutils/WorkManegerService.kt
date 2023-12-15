package com.example.todoapp.workmanagerutils

import android.content.Context
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import org.jetbrains.annotations.Async.Schedule
import java.util.concurrent.TimeUnit

class WorkManagerService(val context: Context) {
    fun schedule(name: String, delay: Long){

        val constraints =androidx.work.Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()

        val request = OneTimeWorkRequestBuilder<NotificationWorker>()

            .addTag(name)
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf("name" to name))
//            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueue(request)
    }
}