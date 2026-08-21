package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notifications",
    indices = [
        Index(value = ["userId"]),
        Index(value = ["createdAt"])
    ]
)
data class NotificationEntity(

    @PrimaryKey
    val id: String,

    val userId: String,

    val title: String,

    val message: String,

    val image: String?,

    val type: String,

    val referenceId: String?,

    val isRead: Boolean,

    val createdAt: Long
)