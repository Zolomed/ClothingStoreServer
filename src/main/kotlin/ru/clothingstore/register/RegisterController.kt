package ru.clothingstore.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.clothingstore.database.tokens.TokenDTO
import ru.clothingstore.database.tokens.Tokens
import ru.clothingstore.database.users.UserDTO
import ru.clothingstore.database.users.Users
import ru.clothingstore.utils.Validators.Companion.isValidPhone
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registerReceiveRemote  = call.receive<RegisterReceiveRemote>()
        if(!isValidPhone(registerReceiveRemote.phone)){
            call.respond(HttpStatusCode.BadRequest, "Phone is not valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.phone)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        phone = registerReceiveRemote.phone,
                        password = registerReceiveRemote.password
                    )
                )

            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")

            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "User could not be created")
            }

            Tokens.insert(
                TokenDTO(
                    phone = registerReceiveRemote.phone,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }
    }
}