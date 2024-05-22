package ru.clothingstore

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import ru.clothingstore.login.configureLoginRouting
import ru.clothingstore.plugins.*
import ru.clothingstore.register.configureRegisterRouting

@Serializable
data class Test(
    val text: String
)

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    routing {
        get("/") {
            val test = Test("Hello World")
            call.respond(test)
        }
    }
    configureLoginRouting()
    configureRegisterRouting()

}
