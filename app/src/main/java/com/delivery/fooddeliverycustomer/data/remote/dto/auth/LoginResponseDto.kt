package com.delivery.fooddeliverycustomer.data.remote.dto.auth

data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val user: UserDto
)