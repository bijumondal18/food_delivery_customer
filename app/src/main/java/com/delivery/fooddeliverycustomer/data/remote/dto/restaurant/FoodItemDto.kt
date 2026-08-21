package com.delivery.fooddeliverycustomer.data.remote.dto.restaurant

data class FoodItemDto(
    val id: String,
    val restaurantId: String,
    val categoryId: String,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Double,
    val discountedPrice: Double?,
    val isVeg: Boolean,
    val isEgg: Boolean,
    val isAvailable: Boolean,
    val isBestSeller: Boolean,
    val rating: Double,
    val totalRatings: Int,
    val tags: List<String>,
    val preparationTime: Int,
    val customizationGroups: List<CustomizationGroupDto>
)