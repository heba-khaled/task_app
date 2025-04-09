package com.heba.task_app.features.user.di

import com.heba.task_app.core.data.local.UserDatabase
import com.heba.task_app.features.user.data.repository.RepositoryImpl
import com.heba.task_app.features.user.data.repository.local.LocalDataSourceImpl
import com.heba.task_app.features.user.domain.repository.IRepository
import com.heba.task_app.features.user.domain.repository.local.ILocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Singleton
    @Provides
    fun provideLocalDS(userDatabase: UserDatabase): ILocalDataSource =
        LocalDataSourceImpl(userDatabase)

    @Singleton
    @Provides
    fun provideRepository(
        localDS: ILocalDataSource
    ): IRepository =
        RepositoryImpl(localDS)
}