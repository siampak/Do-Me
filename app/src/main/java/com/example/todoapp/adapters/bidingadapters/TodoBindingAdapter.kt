package com.example.todoapp.adapters.bidingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todoapp.R
import com.example.todoapp.utils.getFormattedDateTime
import com.example.todoapp.utils.priority_low
import com.example.todoapp.utils.priority_normal



@BindingAdapter("app:setPriorityIcon")
fun setPriorityIcon(iv: ImageView, priority: String){

    val iconResource = when (priority){

        priority_low -> R.drawable.baseline_blue_star_24
        priority_normal -> R.drawable.baseline_green_star_24
        else ->  R.drawable.baseline_red_star_24
    }

    iv.setImageResource(iconResource)
}

@BindingAdapter("app:setFormattedDate")
fun setFormattedDate(tv: TextView, date: Long){
    tv.text = getFormattedDateTime(date, "dd/MM/yyyy")
}

@BindingAdapter("app:setFormattedTime")
fun setFormattedTime(tv: TextView, time: Long){
    tv.text = getFormattedDateTime(time, "hh:mm a")
}