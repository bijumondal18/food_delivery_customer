package com.delivery.fooddeliverycustomer.data.remote.dto.order

data class CreateOrderRequestDto(
    val restaurantId: String,
    val addressId: String,
    val paymentMethod: String,
    val couponCode: String?,
    val notes: String?
)