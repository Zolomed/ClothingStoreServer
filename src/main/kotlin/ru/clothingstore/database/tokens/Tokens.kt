package ru.clothingstore.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: Table() {
    private val phone = Tokens.varchar("phone", 12)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[phone] = tokenDTO.phone
                it[token] = tokenDTO.token
            }
        }
    }
}