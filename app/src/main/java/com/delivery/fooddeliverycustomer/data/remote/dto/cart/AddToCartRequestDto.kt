package com.delivery.fooddeliverycustomer.data.remote.dto.cart

data class AddToCartRequestDto(
    val restaurantId: String,
    val foodItemId: String,
    val quantity: Int,
    val customizations: List<CartCustomizationDto>
)