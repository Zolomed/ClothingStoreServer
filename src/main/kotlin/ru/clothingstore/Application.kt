package ru.clothingstore

import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.clothingstore.features.items.configureItemsRouting
import ru.clothingstore.features.login.configureLoginRouting
import ru.clothingstore.plugins.*
import ru.clothingstore.features.register.configureRegisterRouting

fun main() {
    val config = ConfigFactory.load("application.properties")

    val dbUrl = config.getString("database.url")
    val dbUser = config.getString("database.user")
    val dbPassword = config.getString("database.password")

    Database.connect(
        url = dbUrl,
        driver = "org.postgresql.Driver",
        user = dbUser,
        password = dbPassword
    )

    embeddedServer(
        CIO,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureItemsRouting()
}
