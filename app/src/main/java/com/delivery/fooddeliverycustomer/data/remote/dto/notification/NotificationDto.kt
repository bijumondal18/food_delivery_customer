package com.delivery.fooddeliverycustomer.data.remote.dto.notification

data class NotificationDto(
    val id: String,
    val userId: String,
    val title: String,
    val message: String,
    val image: String?,
    val type: String,
    val referenceId: String?,
    val isRead: Boolean,
    val createdAt: String
)