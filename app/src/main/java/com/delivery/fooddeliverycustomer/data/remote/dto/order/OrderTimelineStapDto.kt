package com.delivery.fooddeliverycustomer.data.remote.dto.order

data class OrderTimelineStepDto(
    val status: String = "",
    val title: String = "",
    val description: String? = null,
    val timestamp: String? = null,
    val completed: Boolean = false
)