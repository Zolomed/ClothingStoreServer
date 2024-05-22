package ru.clothingstore.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.clothingstore.cache.InMemoryCache
import ru.clothingstore.cache.TokenCache
import ru.clothingstore.utils.Validators.Companion.isValidPhone
import java.util.*

class RegisterRouting {
}

fun Application.configureRegisterRouting() {
    routing {

        post("/register") {

            val receive  = call.receive<RegisterReceiveRemote>()
            if(!isValidPhone(receive.phone)){
                call.respond(HttpStatusCode.BadRequest, "Phone is not valid")
            }

            if (InMemoryCache.userList.map {it.phone}.contains(receive.phone)) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            val token = UUID.randomUUID().toString()
            InMemoryCache.userList.add(receive)
            InMemoryCache.token.add(TokenCache(phone = receive.phone, token = token))

            call.respond(RegisterResponseRemote(token = token))
        }
    }
}