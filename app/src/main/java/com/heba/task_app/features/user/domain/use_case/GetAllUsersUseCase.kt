package com.heba.task_app.features.user.domain.use_case

import com.heba.task_app.features.user.domain.repository.IRepository

class GetAllUsersUseCase (private val iRepository: IRepository) {
    suspend operator fun invoke() =
        iRepository.getAllUsers()
}