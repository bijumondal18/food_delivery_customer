package com.delivery.fooddeliverycustomer.data.remote.dto.common

data class ApiResponseDto<T>(
    val success: Boolean,
    val message: String?,
    val data: T?
)