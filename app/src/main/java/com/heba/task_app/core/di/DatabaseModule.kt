package com.heba.task_app.core.di

import android.content.Context
import androidx.room.Room
import com.heba.task_app.core.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Volatile
    private var instance: UserDatabase? = null
    private const val DATABASE_NAME = "UserDB"

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): UserDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}