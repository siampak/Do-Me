package com.example.todoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.todoapp.entities.TodoModel
import com.example.todoapp.repos.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

//class TodoViewModel(application: Application) : AndroidViewModel(application){
//    private val repository: TodoRepository
//
//    init {
//        repository = TodoRepository(application)
//    }


    fun insertTodo(todoModel: TodoModel) {
        viewModelScope.launch {
            repository.insertTodo(todoModel)
        }
    }

    fun fetchAllTodo(): LiveData<List<TodoModel>> {
        return repository.getAllTodos()
    }


    fun updateTodo(todoModel: TodoModel) {

        viewModelScope.launch {

            todoModel.isCompleted = !todoModel.isCompleted
            repository.updateTodo(todoModel)
        }

    }


    fun deleteTodo(todoModel: TodoModel) {

        viewModelScope.launch {

            repository.deleteTodo(todoModel)

        }

    }
}
