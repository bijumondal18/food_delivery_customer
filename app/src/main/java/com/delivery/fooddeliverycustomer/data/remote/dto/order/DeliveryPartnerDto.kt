package com.delivery.fooddeliverycustomer.data.remote.dto.order

data class DeliveryPartnerDto(
    val id: String,
    val name: String,
    val phone: String?,
    val profileImage: String?,
    val rating: Double?,
    val vehicleType: String?,
    val vehicleNumber: String?
)