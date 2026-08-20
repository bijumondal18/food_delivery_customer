package com.delivery.fooddeliverycustomer.data.model.favourite

data class FavoriteRestaurant(
    val id: String = "",
    val userId: String = "",
    val restaurantId: String = "",

    val createdAt: Long = 0L
)

data class FavoriteFood(
    val id: String = "",
    val userId: String = "",
    val foodItemId: String = "",

    val createdAt: Long = 0L
)