package com.delivery.fooddeliverycustomer.data.remote.dto.order

data class OrderStatusDto(
    val status: String,
    val title: String,
    val description: String?,
    val timestamp: String?,
    val completed: Boolean
)