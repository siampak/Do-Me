package com.example.todoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapters.TodoAdapter
import com.example.todoapp.databinding.FragmentToDoListBinding
import com.example.todoapp.db.ToDoDatabase
import com.example.todoapp.dialogs.DeleteConfirmatonDialog
import com.example.todoapp.entities.TodoModel
import com.example.todoapp.utils.todo_delete
import com.example.todoapp.utils.todo_edit
import com.example.todoapp.viewmodels.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoListFragment : Fragment() {

    private lateinit var binding: FragmentToDoListBinding

    private val todoViewModel : TodoViewModel by viewModels()  //import view model


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentToDoListBinding.inflate(inflater,container,false)

        val adapter = TodoAdapter(::todoAction) //For edit Marktodo as completed (check box)

        //recycler view
//        val adapter = TodoAdapter()
        binding.todoRVId.layoutManager = LinearLayoutManager(activity)
        binding.todoRVId.adapter =adapter

//        ToDoDatabase.getDB(requireActivity())
//            .getToDoDao()
//            .getAllTodos()
        todoViewModel.fetchAllTodo().observe(viewLifecycleOwner,{todolist ->

//                Toast.makeText(activity,"${todolist.size}", Toast.LENGTH_SHORT).show()

                adapter.submitList(todolist)

        })

        binding.floatingnewtodoId.setOnClickListener{
            findNavController().navigate(R.id.new_todo_Action)
        }
        return binding.root
    }


    //For edit Marktodo as  completed

 private  fun todoAction(todoModel: TodoModel, tag: String) {


     when (tag) {
         todo_edit -> todoViewModel.updateTodo(todoModel)
         todo_delete -> {


             DeleteConfirmatonDialog{

                 todoViewModel.deleteTodo(todoModel)
             }.show(childFragmentManager, "delete")
         }
     }

 }
}