package com.example.preguntadosicc.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserEntity (
    @PrimaryKey
    @ColumnInfo(name="email") var email: String,
    @ColumnInfo(name="username") var username: String,
    @ColumnInfo(name="token") var token: String,
)