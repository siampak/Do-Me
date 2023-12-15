package com.example.todoapp.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.entities.TodoModel

@Dao
interface ToDoDao {

    @Insert
   suspend fun addToDo (todoModel: TodoModel)


    @Update
   suspend fun updateTodo(todoModel: TodoModel)

    @Delete
   suspend fun deleteTodo(todoModel: TodoModel)

    @Query("select * from tbl_todo order by id desc")
    fun getAllTodos():LiveData<List<TodoModel>>        //without live data use suspend

}