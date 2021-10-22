package com.example.preguntadosicc.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.preguntadosicc.login.models.UserResponse

@Dao
interface UserDao {
    @Query("Select * from userTable Limit 1")
    fun getUser(): List <UserEntity>

    @Query("Delete from userTable")
    fun clearTable()

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertUser (user: UserEntity)
}