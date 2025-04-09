package com.heba.task_app.features.user.data.local

import androidx.room.*
import com.heba.task_app.features.user.data.local.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<UserEntity>
}