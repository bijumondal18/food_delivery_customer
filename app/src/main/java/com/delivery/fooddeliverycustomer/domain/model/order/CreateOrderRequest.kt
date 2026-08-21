package com.delivery.fooddeliverycustomer.domain.model.order

data class CreateOrderRequest(
    val restaurantId: String,
    val addressId: String,
    val paymentMethod: String,
    val couponCode: String? = null,
    val notes: String? = null
)