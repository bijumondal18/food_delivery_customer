package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "order_tracking"
)
data class OrderTrackingEntity(

    @PrimaryKey
    val orderId: String,

    val orderStatus: String?,

    val restaurantStatus: String?,

    val deliveryStatus: String?,

    val estimatedDeliveryTime: String?,

    val deliveryPartnerJson: String?,

    val currentLocationJson: String?,

    val timelineJson: String
)