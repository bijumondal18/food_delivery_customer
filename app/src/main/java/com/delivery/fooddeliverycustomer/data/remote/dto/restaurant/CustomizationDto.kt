package com.delivery.fooddeliverycustomer.data.remote.dto.restaurant

data class CustomizationGroupDto(
    val id: String,
    val foodItemId: String,
    val name: String,
    val type: String,
    val isRequired: Boolean,
    val minSelection: Int,
    val maxSelection: Int,
    val options: List<CustomizationOptionDto>
)