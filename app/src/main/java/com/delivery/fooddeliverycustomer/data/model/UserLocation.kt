package com.delivery.fooddeliverycustomer.data.model

data class UserLocation(
    val latitude: Double,
    val longitude: Double,
    val address: String = ""
)