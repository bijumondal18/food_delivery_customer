package com.delivery.fooddeliverycustomer.data.remote.dto.auth

data class VerifyOtpRequestDto(
    val phone: String,
    val otp: String
)