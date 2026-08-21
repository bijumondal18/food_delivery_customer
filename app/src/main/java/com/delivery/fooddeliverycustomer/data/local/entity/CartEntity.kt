package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class CartEntity(

    @PrimaryKey
    val id: String,

    val userId: String,

    val restaurantId: String,

    val restaurantName: String,

    val restaurantImage: String?,

    val subtotal: Double,

    val deliveryFee: Double,

    val platformFee: Double,

    val tax: Double,

    val discount: Double,

    val totalAmount: Double,

    val syncStatus: String,

    val updatedAt: Long
)