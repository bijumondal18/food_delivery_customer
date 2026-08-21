package com.delivery.fooddeliverycustomer.data.remote.dto.restaurant

data class RestaurantAddressDto(
    val addressLine1: String,
    val addressLine2: String?,
    val landmark: String?,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String
)