package ru.clothingstore.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val phone: String,
    val password: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)