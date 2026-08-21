package com.delivery.fooddeliverycustomer.data.remote.dto.restaurant

data class RestaurantDto(
    val id: String,
    val name: String,
    val description: String?,
    val logo: String?,
    val images: List<RestaurantImageDto>,
    val cuisines: List<String>,
    val rating: Double,
    val totalRatings: Int,
    val deliveryTime: Int,
    val deliveryFee: Double,
    val minimumOrderAmount: Double,
    val isOpen: Boolean,
    val isPureVeg: Boolean,
    val isPromoted: Boolean,
    val address: RestaurantAddressDto?,
    val latitude: Double?,
    val longitude: Double?,
    val createdAt: String,
    val updatedAt: String
)