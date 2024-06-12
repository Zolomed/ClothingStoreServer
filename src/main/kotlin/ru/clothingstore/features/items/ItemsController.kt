package ru.clothingstore.features.items

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.clothingstore.database.item.Items
import ru.clothingstore.database.item.mapToCreateItemResponse
import ru.clothingstore.database.item.mapToItemDTO
import ru.clothingstore.database.item.mapToItemResponse

class ItemsController(private val call: ApplicationCall) {

    suspend fun fetchAllItems() {
        call.respond(
            FetchItemsResponse(
                items = Items.fetchItems().map { it.mapToItemResponse() }
            )
        )
    }

    suspend fun addItem() {
        val request = call.receive<CreateItemRequest>()
        val item = request.mapToItemDTO()
        Items.insert(item)
        call.respond(item.mapToCreateItemResponse())
    }
}