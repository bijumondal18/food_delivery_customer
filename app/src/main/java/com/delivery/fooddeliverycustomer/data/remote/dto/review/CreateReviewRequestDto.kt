package com.delivery.fooddeliverycustomer.data.remote.dto.review

data class CreateReviewRequestDto(
    val orderId: String,
    val restaurantId: String?,
    val foodItemId: String?,
    val rating: Int,
    val comment: String?,
    val images: List<String>
)