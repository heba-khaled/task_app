package com.heba.task_app.features.user.data.repository.local

import com.heba.task_app.core.data.local.UserDatabase
import com.heba.task_app.features.user.data.local.entities.UserEntity
import com.heba.task_app.features.user.domain.model.User
import com.heba.task_app.features.user.domain.repository.local.ILocalDataSource

class LocalDataSourceImpl(
    private val userDatabase: UserDatabase
) : ILocalDataSource {
    override suspend fun addUser(user: UserEntity) {
        userDatabase.userDao().insertUser(user)
    }

    override suspend fun getAllUsers(): List<User> =
        userDatabase.userDao().getAllUsers().map {
            it.mapToDomain()
        }
}