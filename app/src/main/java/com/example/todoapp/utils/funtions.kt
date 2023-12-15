package com.example.todoapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedDateTime(millis: Long, format:String) =
    SimpleDateFormat(format, Locale.getDefault()).format(Date(millis))