package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "order_timeline"
)
data class OrderTimelineStepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderId: String,
    val status: String,
    val title: String,
    val description: String?,
    val timestamp: String?,
    val completed: Boolean
)