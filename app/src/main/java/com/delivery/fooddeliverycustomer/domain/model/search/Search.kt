package com.delivery.fooddeliverycustomer.domain.model.search

import com.delivery.fooddeliverycustomer.domain.model.Restaurant
import com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodItem

data class SearchResult(
    val restaurants: List<com.delivery.fooddeliverycustomer.domain.model.Restaurant> = emptyList(),
    val foodItems: List<com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodItem> = emptyList()
)

data class SearchHistory(
    val id: String = "",
    val userId: String = "",
    val query: String = "",
    val createdAt: Long = 0L
)