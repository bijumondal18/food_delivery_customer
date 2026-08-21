package com.delivery.fooddeliverycustomer.data.remote.dto.auth

data class UserDto(
    val id: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val profileImage: String?,
    val isEmailVerified: Boolean,
    val isPhoneVerified: Boolean,
    val createdAt: String,
    val updatedAt: String
)