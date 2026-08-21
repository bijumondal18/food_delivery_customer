package com.delivery.fooddeliverycustomer.data.remote.dto.review

data class ReviewDto(
    val id: String,
    val userId: String,
    val userName: String,
    val userProfileImage: String?,
    val restaurantId: String?,
    val foodItemId: String?,
    val rating: Int,
    val comment: String?,
    val images: List<String>,
    val createdAt: String
)