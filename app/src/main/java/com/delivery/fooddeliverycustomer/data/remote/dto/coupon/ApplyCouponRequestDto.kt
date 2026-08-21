package com.delivery.fooddeliverycustomer.data.remote.dto.coupon

data class ApplyCouponRequestDto(
    val code: String,
    val cartId: String
)