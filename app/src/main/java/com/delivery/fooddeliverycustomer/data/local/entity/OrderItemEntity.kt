package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "order_items",
    primaryKeys = ["orderId", "foodItemId"],
    indices = [
        Index(value = ["orderId"])
    ]
)
data class OrderItemEntity(

    val orderId: String,

    val foodItemId: String,

    val name: String,

    val image: String?,

    val price: Double,

    val quantity: Int,

    val totalPrice: Double
)