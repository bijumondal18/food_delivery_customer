package com.delivery.fooddeliverycustomer.data.model.user

import com.delivery.fooddeliverycustomer.data.model.location.Address

data class User(
    val id: String = "",

    val name: String = "",
    val email: String = "",
    val phone: String = "",

    val profileImage: String? = null,

    val addresses: List<Address> = emptyList(),

    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)