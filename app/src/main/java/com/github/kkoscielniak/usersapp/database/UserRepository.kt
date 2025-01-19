package com.github.kkoscielniak.usersapp.database

import com.github.kkoscielniak.usersapp.users.UserItem
import com.github.kkoscielniak.usersapp.database.database.UserEntity
import com.github.kkoscielniak.usersapp.database.database.UsersDao
import com.github.kkoscielniak.usersapp.database.database.toUserItem
import com.github.kkoscielniak.usersapp.database.database.toUserDetails
import com.github.kkoscielniak.usersapp.users.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UsersRepository {
    val users: Flow<List<UserItem>>

    suspend fun findDetails(id: Int): Flow<UserDetails?>

    suspend fun save(users: List<UserEntity>)

}

class UsersRepositoryImpl @Inject constructor(private val dao: UsersDao) : UsersRepository {

    override val users: Flow<List<UserItem>> = dao.findAll().map {
        items -> items.map { it.toUserItem() }
    }


    override suspend fun findDetails(id: Int): Flow<UserDetails?> {
       return dao.findById(id).map { it.toUserDetails() }
    }

    override suspend fun save(users: List<UserEntity>) {
        dao.save(users)
    }
}