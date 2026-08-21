package com.delivery.fooddeliverycustomer.data.remote.dto.restaurant

data class FoodCategoryDto(
    val id: String,
    val restaurantId: String,
    val name: String,
    val description: String?,
    val image: String?,
    val sortOrder: Int,
    val isActive: Boolean
)