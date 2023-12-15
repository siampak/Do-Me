package com.example.todoapp.repos

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.todoapp.daos.ToDoDao
import com.example.todoapp.db.ToDoDatabase
import com.example.todoapp.entities.TodoModel
import javax.inject.Inject

class TodoRepository @Inject constructor(private val toDoDao: ToDoDao) {

//class  TodoRepository(val context: Context){
//    private val toDoDao:ToDoDao = ToDoDatabase.getDB(context).getToDoDao()


    suspend fun insertTodo(todoModel: TodoModel){
        toDoDao.addToDo(todoModel)
    }

    fun getAllTodos() : LiveData<List<TodoModel>> {
        return  toDoDao.getAllTodos()
    }

    suspend fun updateTodo(todoModel: TodoModel) {

        toDoDao.updateTodo(todoModel)

    }

    suspend fun deleteTodo(todoModel: TodoModel) {

        toDoDao.deleteTodo(todoModel)

    }
}