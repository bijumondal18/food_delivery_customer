package com.delivery.fooddeliverycustomer.domain.model.location

data class Address(
    val id: String = "",
    val userId: String = "",

    val label: AddressLabel = AddressLabel.HOME,

    val name: String = "",
    val phone: String = "",

    val addressLine1: String = "",
    val addressLine2: String = "",
    val landmark: String = "",

    val city: String = "",
    val state: String = "",
    val country: String = "India",
    val postalCode: String = "",

    val latitude: Double = 0.0,
    val longitude: Double = 0.0,

    val isDefault: Boolean = false
)

enum class AddressLabel {
    HOME,
    WORK,
    OTHER
}

data class Location(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)