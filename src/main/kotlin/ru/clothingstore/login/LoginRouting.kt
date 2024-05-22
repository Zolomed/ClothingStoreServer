package ru.clothingstore.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.clothingstore.cache.InMemoryCache
import ru.clothingstore.cache.TokenCache
import java.util.UUID

class LoginRouting {
}

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val receive = call.receive<LoginReceiveRemote>()
            val first = InMemoryCache.userList.firstOrNull() { it.phone == receive.phone }

            if (first == null){
                call.respond(HttpStatusCode.BadRequest, "User not found")
            } else {
                if (first.password == receive.password){
                    val token = UUID.randomUUID().toString()
                    InMemoryCache.token.add(TokenCache(phone = receive.phone, token = token))
                    call.respond(LoginResponseRemote(token = token))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid password")
                }
            }


        }
    }
}