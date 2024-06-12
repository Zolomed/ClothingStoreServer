package ru.clothingstore.features.items

import kotlinx.serialization.Serializable

@Serializable
data class CreateItemRequest(
    val name: String,
    val price: Double,
    val image: String?
)

@Serializable
data class CreateItemResponse(
    val itemId: String,
    val name: String,
    val price: Double,
)