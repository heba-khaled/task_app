package com.heba.task_app.features.user.data.repository

import com.heba.task_app.core.common.Resource
import com.heba.task_app.core.data.base.BaseRepository
import com.heba.task_app.features.user.data.local.entities.UserEntity
import com.heba.task_app.features.user.domain.model.User
import com.heba.task_app.features.user.domain.repository.IRepository
import com.heba.task_app.features.user.domain.repository.local.ILocalDataSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localDS: ILocalDataSource
) : IRepository, BaseRepository() {
    override suspend fun addUser(user: UserEntity) {
        localDS.addUser(user)
    }

    override suspend fun getAllUsers(): Flow<Resource<List<User>>> =
        executeFun { localDS.getAllUsers() }
}