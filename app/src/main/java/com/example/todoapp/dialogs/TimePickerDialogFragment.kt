package com.example.todoapp.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentHostCallback
import java.util.Calendar

class TimePickerDialogFragment(val callback: (Long) -> Unit) :DialogFragment(),TimePickerDialog.OnTimeSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //get a calender
        val calender = Calendar.getInstance()
        val hour =calender.get(Calendar.HOUR)
        val minute =calender.get(Calendar.MINUTE)

        return TimePickerDialog(requireActivity(),this,hour,minute,false)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {


        val calendar = Calendar.getInstance()
        calendar.set(0,0,0,hourOfDay,minute)

        callback(calendar.timeInMillis)

    }
}