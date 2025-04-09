package com.heba.task_app.features.user.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heba.task_app.features.user.domain.model.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    @ColumnInfo(name = "user_name")
    var userName: String,
    @ColumnInfo(name = "user_age")
    var userAge: Int,
    @ColumnInfo(name = "user_job")
    var userJob: String,
    @ColumnInfo(name = "user_gender")
    var userGender: String,
){
   fun mapToDomain(): User {
       return User(
           userId = userId,
           userName = userName,
           userAge = userAge,
           userJob = userJob,
           userGender = userGender
       )
   }
}
