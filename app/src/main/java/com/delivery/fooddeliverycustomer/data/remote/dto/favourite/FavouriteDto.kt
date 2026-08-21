package com.delivery.fooddeliverycustomer.data.remote.dto.favourite

data class FavoriteDto(
    val id: String,
    val userId: String,
    val itemId: String,
    val type: String,
    val createdAt: String
)