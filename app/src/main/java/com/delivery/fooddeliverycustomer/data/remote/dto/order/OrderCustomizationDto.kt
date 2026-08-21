package com.delivery.fooddeliverycustomer.data.remote.dto.order

data class OrderCustomizationDto(
    val groupId: String,
    val groupName: String,
    val optionId: String,
    val optionName: String,
    val price: Double
)