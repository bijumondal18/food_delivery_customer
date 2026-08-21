package com.delivery.fooddeliverycustomer.data.remote.dto.auth

data class RegisterRequestDto(
    val name: String,
    val email: String?,
    val phone: String?,
    val password: String
)