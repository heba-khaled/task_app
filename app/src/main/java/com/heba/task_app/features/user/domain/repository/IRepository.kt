package com.heba.task_app.features.user.domain.repository

import com.heba.task_app.core.common.Resource
import com.heba.task_app.features.user.data.local.entities.UserEntity
import com.heba.task_app.features.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun addUser(user: UserEntity)
    suspend fun getAllUsers(): Flow<Resource<List<User>>>
}