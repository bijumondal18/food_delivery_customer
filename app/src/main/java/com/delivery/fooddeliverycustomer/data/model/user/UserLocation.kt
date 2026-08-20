package com.delivery.fooddeliverycustomer.data.model.user

data class UserLocation(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,

    val address: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = ""
)