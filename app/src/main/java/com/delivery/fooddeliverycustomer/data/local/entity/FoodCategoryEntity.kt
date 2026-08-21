package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "food_categories",
    indices = [
        Index(value = ["restaurantId"])
    ]
)
data class FoodCategoryEntity(

    @PrimaryKey
    val id: String,

    val restaurantId: String,

    val name: String,

    val description: String,

    val image: String?,

    val sortOrder: Int,

    val isActive: Boolean
)