package com.github.kkoscielniak.usersapp.database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun findAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun findById(id: Int): Flow<UserEntity>
}