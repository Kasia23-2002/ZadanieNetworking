package com.github.kkoscielniak.usersapp.users

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kkoscielniak.usersapp.database.UsersRepository
import com.github.kkoscielniak.usersapp.database.database.toUserEntity
import com.github.kkoscielniak.usersapp.database.database.toUserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _users = MutableStateFlow<List<UserItem>>(emptyList())
    val users: StateFlow<List<UserItem>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            if (isOnline()) {
                try {
                    val response = RetrofitInstance.api.getUsers()
                    val userEntities = response.map { it.toUserEntity() }
                    usersRepository.save(userEntities)
                    _users.value = response.map { it.toUserItem() }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                usersRepository.users.map { userEntities ->
                    _users.value = userEntities
                }
            }
        }
    }


    private fun isOnline(): Boolean {
        val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
        if (connectivityManager != null) {
            val network = connectivityManager.activeNetwork
            if (network != null) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if (networkCapabilities != null) {
                    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                }
            }
        }
        return false
    }
}