package ru.clothingstore.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.clothingstore.Test

fun Application.configureRouting() {
    routing {
        get("/") {
            val test = Test("123")
            call.respond(test)
        }
    }
}
