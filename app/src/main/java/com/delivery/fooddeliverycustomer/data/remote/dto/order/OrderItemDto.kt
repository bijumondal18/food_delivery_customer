package com.delivery.fooddeliverycustomer.data.remote.dto.order

data class OrderItemDto(
    val foodItemId: String,
    val name: String,
    val image: String?,
    val price: Double,
    val quantity: Int,
    val totalPrice: Double,
    val customizations: List<OrderCustomizationDto>
)