package com.delivery.fooddeliverycustomer.data.remote.dto.cart

data class UpdateCartItemRequestDto(
    val quantity: Int,
    val customizations: List<CartCustomizationDto>
)