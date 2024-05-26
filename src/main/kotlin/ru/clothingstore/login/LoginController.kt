package ru.clothingstore.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.clothingstore.database.tokens.TokenDTO
import ru.clothingstore.database.tokens.Tokens
import ru.clothingstore.database.users.Users
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(receive.phone)

        if (userDTO == null){
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == receive.password){
                val token = UUID.randomUUID().toString()
                Tokens.insert(TokenDTO(phone = receive.phone, token = token))
                call.respond(LoginResponseRemote(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}