package ru.clothingstore.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table() {
    private val phone = Users.varchar("phone", 12)
    private val password = Users.varchar("password", 32)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[phone] = userDTO.phone
                it[password] = userDTO.password
            }
        }
    }

    fun fetchUser(phone: String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.phone.eq(phone) }.single()
                UserDTO(
                    phone = userModel[Users.phone],
                    password = userModel[password]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}