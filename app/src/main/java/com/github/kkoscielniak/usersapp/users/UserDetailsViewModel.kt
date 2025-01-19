package com.github.kkoscielniak.usersapp.users
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kkoscielniak.usersapp.database.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: UsersRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserDetails?>(null)
    val user: StateFlow<UserDetails?> = _user

    fun fetchUserById(id: Int) {
        viewModelScope.launch {
            repository.findDetails(id).collect { user ->
                _user.value = user
            }
        }
    }
}