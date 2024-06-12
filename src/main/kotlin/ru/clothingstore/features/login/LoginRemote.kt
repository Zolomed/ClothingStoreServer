package ru.clothingstore.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginReceiveRemote(
    val phone: String,
    val password: String
)

@Serializable
data class LoginResponseRemote(
    val token: String
)