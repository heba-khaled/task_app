package com.heba.task_app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heba.task_app.features.user.data.local.UserDao
import com.heba.task_app.features.user.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}