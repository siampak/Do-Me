package com.example.todoapp.di

import android.content.Context
import com.example.todoapp.daos.ToDoDao
import com.example.todoapp.db.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Provides
    fun provideTodoDao(@ApplicationContext context: Context) : ToDoDao {
        return  ToDoDatabase.getDB(context).getToDoDao()
    }
}