package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "cart_items",
    primaryKeys = ["cartId", "foodItemId"],
    indices = [
        Index(value = ["cartId"])
    ]
)
data class CartItemEntity(

    val cartId: String,

    val foodItemId: String,

    val name: String,

    val image: String?,

    val basePrice: Double,

    val finalPrice: Double,

    val quantity: Int,

    val totalPrice: Double
)