package com.delivery.fooddeliverycustomer.data.remote.dto.restaurant

data class CustomizationOptionDto(
    val id: String,
    val groupId: String,
    val name: String,
    val price: Double,
    val isAvailable: Boolean
)