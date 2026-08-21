package com.delivery.fooddeliverycustomer.domain.model.user

import com.delivery.fooddeliverycustomer.domain.model.location.Address

data class User(
    val id: String = "",

    val name: String = "",
    val email: String = "",
    val phone: String = "",

    val profileImage: String? = null,

    val addresses: List<com.delivery.fooddeliverycustomer.domain.model.location.Address> = emptyList(),

    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)