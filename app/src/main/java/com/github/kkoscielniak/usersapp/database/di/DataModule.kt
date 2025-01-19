package com.github.kkoscielniak.usersapp.database.di

import com.github.kkoscielniak.usersapp.database.UsersRepository
import com.github.kkoscielniak.usersapp.database.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindUsersRepository(repository: UsersRepositoryImpl): UsersRepository
}