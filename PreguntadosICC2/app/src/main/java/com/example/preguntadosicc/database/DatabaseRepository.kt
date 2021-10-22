package com.example.preguntadosicc.database

import android.app.Application
import androidx.room.Room
import com.example.preguntadosicc.database.user.UserDao

class UserDatabaseRepositiry(application: Application) {
    private val database = Room.databaseBuilder(application, AppDatabase::class.java, "user")
        .fallbackToDestructiveMigration()
        .build()

    fun getUserDao(): UserDao {
        return database.userDao()
    }
}