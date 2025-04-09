package com.heba.task_app.features.user.domain.use_case

import com.heba.task_app.features.user.data.local.entities.UserEntity
import com.heba.task_app.features.user.domain.repository.IRepository

class AddUserUseCase(private val iRepository: IRepository) {
    suspend operator fun invoke(user: UserEntity) =
        iRepository.addUser(user)
}