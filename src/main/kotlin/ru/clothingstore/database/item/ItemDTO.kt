package ru.clothingstore.database.item

import ru.clothingstore.features.items.CreateItemRequest
import ru.clothingstore.features.items.CreateItemResponse
import ru.clothingstore.features.items.ItemsResponse
import java.util.*

data class ItemDTO(
    val itemId: String,
    val name: String,
    val price: Double,
    val image: String?
)

fun CreateItemRequest.mapToItemDTO(): ItemDTO =
    ItemDTO(
        itemId = UUID.randomUUID().toString(),
        name = name,
        price = price,
        image = "http://192.168.1.78:8080/clothes/$image"
    )

fun ItemDTO.mapToCreateItemResponse(): CreateItemResponse =
    CreateItemResponse(
        itemId = itemId,
        name = name,
        price = price
    )

fun ItemDTO.mapToItemResponse(): ItemsResponse =
    ItemsResponse(
        itemId = itemId,
        name = name,
        price = price,
        image = "http://192.168.1.78:8080/clothes/$image"
    )