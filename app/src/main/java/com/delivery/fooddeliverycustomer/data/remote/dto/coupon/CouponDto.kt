package com.delivery.fooddeliverycustomer.data.remote.dto.coupon

data class CouponDto(
    val id: String,
    val code: String,
    val title: String,
    val description: String?,
    val discountType: String,
    val discountValue: Double,
    val maximumDiscount: Double?,
    val minimumOrderAmount: Double?,
    val expiresAt: String,
    val isApplicable: Boolean
)