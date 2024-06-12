package ru.clothingstore.database.item

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Items : Table() {
    private val itemId = Items.varchar("itemId", 50)
    private val name = Items.varchar("name", 100)
    private val price = Items.double("price")
    private val image = Items.varchar("image", 100).nullable()

    fun insert(itemDTO: ItemDTO) {
        transaction {
            Items.insert {
                it[itemId] = itemDTO.itemId
                it[name] = itemDTO.name
                it[price] = itemDTO.price
                it[image] = itemDTO.image
            }
        }
    }

    fun fetchItems(): List<ItemDTO> {
        return try {
            transaction {
                Items.selectAll().toList()
                    .map {
                        ItemDTO(
                            itemId = it[itemId],
                            name = it[name],
                            price = it[price],
                            image = it[image]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}