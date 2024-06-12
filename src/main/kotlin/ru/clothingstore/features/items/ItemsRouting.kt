package ru.clothingstore.features.items

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureItemsRouting() {
    routing {
        get("/items/fetch") {
            val itemsController = ItemsController(call)
            itemsController.fetchAllItems()
        }

        post("/items/add") {
            val itemsController = ItemsController(call)
            itemsController.addItem()
        }
    }
}