package com.github.kkoscielniak.usersapp.database.di

import android.content.Context
import androidx.room.Room
import com.github.kkoscielniak.usersapp.database.database.AppDatabase
import com.github.kkoscielniak.usersapp.database.database.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideUsersDao(appDatabase: AppDatabase): UsersDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "UsersDB"
        ).build()
    }
}