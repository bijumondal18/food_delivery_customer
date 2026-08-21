package com.delivery.fooddeliverycustomer.data.remote.dto.cart

data class CartCustomizationDto(
    val groupId: String,
    val groupName: String,
    val optionId: String,
    val optionName: String,
    val price: Double
)