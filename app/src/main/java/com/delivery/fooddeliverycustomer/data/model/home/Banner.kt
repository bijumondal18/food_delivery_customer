package com.delivery.fooddeliverycustomer.data.model.home

data class Banner(
    val id: String = "",
    val title: String = "",
    val subtitle: String = "",

    val image: String = "",

    val actionType: BannerActionType = BannerActionType.NONE,

    val actionId: String? = null,

    val isActive: Boolean = true,

    val sortOrder: Int = 0,

    val validFrom: Long = 0L,
    val validUntil: Long = 0L
)

enum class BannerActionType {
    NONE,
    RESTAURANT,
    FOOD_ITEM,
    CATEGORY,
    COUPON
}