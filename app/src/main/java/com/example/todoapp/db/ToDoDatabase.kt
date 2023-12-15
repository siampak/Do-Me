package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.daos.ToDoDao
import com.example.todoapp.entities.TodoModel

@Database(entities =[TodoModel::class], version = 1)
abstract class ToDoDatabase :RoomDatabase(){
    abstract  fun getToDoDao() :ToDoDao


    companion object{
        private var toDoDatabase: ToDoDatabase? =null


        fun getDB(context: Context) : ToDoDatabase {
            return toDoDatabase ?: synchronized(this){   //synchronized= bahire theke jeno same time ekono object ke modify korte na pare/ekjoner jonnoi lock kore deoya hoy.
                val db = Room.databaseBuilder(context,ToDoDatabase ::class.java,"todo_db")
//                    .allowMainThreadQueries()  //for test purpose , never use
                    .build()  //build method er maddome database ekti object passi
                toDoDatabase=db
                db //for the first time return db object next time return toDoDatabase


            }
        }

    }




}