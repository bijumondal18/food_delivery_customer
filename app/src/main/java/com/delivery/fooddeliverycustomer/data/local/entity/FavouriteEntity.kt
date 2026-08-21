package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "favorites",
    primaryKeys = ["userId", "itemId", "type"]
)
data class FavoriteEntity(

    val userId: String,

    val itemId: String,

    val type: String,

    val createdAt: Long
)

enum class FavoriteType {
    RESTAURANT,
    FOOD
}