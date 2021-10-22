package com.example.preguntadosicc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.preguntadosicc.database.user.UserDao
import com.example.preguntadosicc.database.user.UserEntity

@Database(entities = [UserEntity:: class], version = 2, exportSchema = false)

abstract class AppDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
}