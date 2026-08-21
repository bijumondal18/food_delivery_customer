package com.delivery.fooddeliverycustomer.data.remote.dto.address

data class UpdateAddressRequestDto(
    val label: String,
    val name: String,
    val phone: String,
    val addressLine1: String,
    val addressLine2: String?,
    val landmark: String?,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val latitude: Double,
    val longitude: Double,
    val isDefault: Boolean
)