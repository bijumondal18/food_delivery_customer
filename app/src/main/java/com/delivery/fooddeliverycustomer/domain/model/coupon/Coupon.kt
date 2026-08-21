package com.delivery.fooddeliverycustomer.domain.model.coupon

data class Coupon(
    val id: String = "",
    val code: String = "",
    val title: String = "",
    val description: String = "",

    val discountType: com.delivery.fooddeliverycustomer.domain.model.coupon.DiscountType = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.coupon.DiscountType.FLAT,
    val discountValue: Double = 0.0,

    val maximumDiscount: Double? = null,
    val minimumOrderValue: Double = 0.0,

    val validFrom: Long = 0L,
    val validUntil: Long = 0L,

    val isActive: Boolean = true
)

enum class DiscountType {
    FLAT,
    PERCENTAGE
}