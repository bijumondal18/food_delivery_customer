package com.delivery.fooddeliverycustomer.data.remote.dto.cart

data class CartItemDto(
    val foodItemId: String,
    val name: String,
    val image: String?,
    val basePrice: Double,
    val finalPrice: Double,
    val quantity: Int,
    val totalPrice: Double,
    val customizations: List<CartCustomizationDto>
)