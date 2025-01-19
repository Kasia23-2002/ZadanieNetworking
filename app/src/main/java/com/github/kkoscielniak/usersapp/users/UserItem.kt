package com.github.kkoscielniak.usersapp.users

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserItem(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: String,
    @SerialName("website") val website: String,
)
