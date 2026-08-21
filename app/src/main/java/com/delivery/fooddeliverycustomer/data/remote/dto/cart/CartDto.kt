package com.delivery.fooddeliverycustomer.data.remote.dto.cart

data class CartDto(
    val id: String,
    val userId: String,
    val restaurantId: String,
    val restaurantName: String,
    val restaurantImage: String?,
    val items: List<CartItemDto>,
    val subtotal: Double,
    val deliveryFee: Double,
    val platformFee: Double,
    val tax: Double,
    val discount: Double,
    val totalAmount: Double,
    val couponCode: String?,
    val updatedAt: String
)