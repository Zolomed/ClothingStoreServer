package ru.clothingstore.features.items

import kotlinx.serialization.Serializable

@Serializable
data class FetchItemsResponse(
    val items: List<ItemsResponse>
)

@Serializable
data class ItemsResponse(
    val itemId: String,
    val name: String,
    val price: Double,
    val image: String?
)