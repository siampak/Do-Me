package com.example.todoapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.todoapp.R

class DeleteConfirmatonDialog(val callback: () -> Unit) : DialogFragment(){

    override fun onCreateDialog (savedInstanceState: Bundle?): Dialog{
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Delete")
        builder.setIcon(R.drawable.baseline_delete_24)
        builder.setMessage("Are you sure to delete this item?")
        builder.setPositiveButton("yes") { dialog, which ->

            callback()

        }

        builder.setNegativeButton(("Cancel"), null)

        return builder.create()
    }
}