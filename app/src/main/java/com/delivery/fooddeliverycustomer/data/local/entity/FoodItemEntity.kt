package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "food_items",
    indices = [
        Index(value = ["restaurantId"]),
        Index(value = ["categoryId"]),
        Index(value = ["name"])
    ]
)
data class FoodItemEntity(

    @PrimaryKey
    val id: String,

    val restaurantId: String,

    val categoryId: String,

    val name: String,

    val description: String,

    val image: String?,

    val price: Double,

    val discountedPrice: Double?,

    val isVeg: Boolean,

    val isEgg: Boolean,

    val isAvailable: Boolean,

    val isBestSeller: Boolean,

    val rating: Double,

    val totalRatings: Int,

    val tags: List<String>,

    val preparationTime: Int
)