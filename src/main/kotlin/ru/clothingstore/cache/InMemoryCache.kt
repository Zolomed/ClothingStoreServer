package ru.clothingstore.cache

import ru.clothingstore.register.RegisterReceiveRemote

data class TokenCache(
    val phone: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}