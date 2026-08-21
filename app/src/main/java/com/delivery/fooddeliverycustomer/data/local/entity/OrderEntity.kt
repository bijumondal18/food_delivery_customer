package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "orders",
    indices = [
        Index(value = ["userId"]),
        Index(value = ["orderNumber"])
    ]
)
data class OrderEntity(

    @PrimaryKey
    val id: String,

    val orderNumber: String,

    val userId: String,

    val restaurantId: String,

    val restaurantName: String,

    val restaurantImage: String?,

    val itemsJson: String,

    val deliveryAddressJson: String?,

    val subtotal: Double,

    val deliveryFee: Double,

    val platformFee: Double,

    val tax: Double,

    val discount: Double,

    val totalAmount: Double,

    val paymentMethod: String,

    val paymentStatus: String,

    val orderStatus: String,

    val couponCode: String?,

    val estimatedDeliveryTime: Long?,

    val createdAt: Long,

    val updatedAt: Long
)