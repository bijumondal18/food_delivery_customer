package com.delivery.fooddeliverycustomer.domain.model.restaurant

data class Review(
    val id: String = "",

    val userId: String = "",
    val userName: String = "",
    val userImage: String? = null,

    val restaurantId: String = "",
    val orderId: String = "",

    val rating: Double = 0.0,
    val comment: String = "",

    val images: List<String> = emptyList(),

    val createdAt: Long = 0L
)