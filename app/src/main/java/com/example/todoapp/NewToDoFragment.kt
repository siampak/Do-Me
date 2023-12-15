package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentNewToDoBinding
import com.example.todoapp.db.ToDoDatabase
import com.example.todoapp.dialogs.DatePickerDialogFragment
import com.example.todoapp.dialogs.TimePickerDialogFragment
import com.example.todoapp.entities.TodoModel
import com.example.todoapp.utils.getFormattedDateTime
import com.example.todoapp.utils.priority_normal
import com.example.todoapp.viewmodels.TodoViewModel
import com.example.todoapp.workmanagerutils.WorkManagerService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewToDoFragment : Fragment() {

    private lateinit var binding: FragmentNewToDoBinding  //Data binding
    private var priority = priority_normal

    private var dateInMillis = System.currentTimeMillis()
    private var timeInMillis = System.currentTimeMillis()

    private val  todoViewModel : TodoViewModel by viewModels()  //self create view model


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewToDoBinding.inflate(inflater, container, false ) //Data binding

        binding.priorityRgId.setOnCheckedChangeListener{radioGroup, i ->

           val rb = radioGroup.findViewById<RadioButton>(i)
            priority = rb.text.toString()
            Toast.makeText(activity,priority,Toast.LENGTH_SHORT).show()

        }



        //For Date
        binding.datebtnId.setOnClickListener{


            DatePickerDialogFragment { timeStamp ->

                dateInMillis = timeStamp
                binding.datebtnId.text = getFormattedDateTime(dateInMillis, "dd/MM/yyyy")

            }.show(childFragmentManager, "date_picker")
        }



        //For Time
        binding.timebtnId.setOnClickListener{

            TimePickerDialogFragment{ timeStamp ->

                timeInMillis =timeStamp
                binding.timebtnId.text = getFormattedDateTime(timeInMillis, "hh:mm: a")

            }.show(childFragmentManager,"time_picker")

        }




        binding.savebtnId.setOnClickListener{
            val todoName  =binding.newtodoId.text.toString()

            //check Edit text empty or not?
            if (todoName.isEmpty()){
                binding.newtodoId.error = "please provide a valid ToDo name"
                return@setOnClickListener
            }



            val todo =TodoModel(name = todoName, priority = priority, date = dateInMillis, time = timeInMillis)

            /* ToDoDatabase.getDB(requireActivity()).getToDoDao().addToDo(todo)  //for without view model */

            todoViewModel.insertTodo(todo)

            WorkManagerService(requireContext()).schedule(todoName, 10000)  //For work manager(notification)

            findNavController().navigate(R.id.todoList_ActionId)

        }


        return binding.root
    }


}