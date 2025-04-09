package com.heba.task_app.features.user.domain.repository.local

import com.heba.task_app.features.user.data.local.entities.UserEntity
import com.heba.task_app.features.user.domain.model.User

interface ILocalDataSource {
    suspend fun addUser(user: UserEntity)
    suspend fun getAllUsers(): List<User>
}