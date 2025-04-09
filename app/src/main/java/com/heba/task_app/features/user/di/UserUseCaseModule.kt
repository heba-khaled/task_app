package com.heba.task_app.features.user.di

import com.heba.task_app.features.user.domain.repository.IRepository
import com.heba.task_app.features.user.domain.use_case.AddUserUseCase
import com.heba.task_app.features.user.domain.use_case.GetAllUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {

    @Singleton
    @Provides
    fun provideAddUserUseCase(repository: IRepository): AddUserUseCase = AddUserUseCase(repository)

    @Singleton
    @Provides
    fun provideGetAllUsersUseCase(repository: IRepository): GetAllUsersUseCase = GetAllUsersUseCase(repository)

}