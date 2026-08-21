package com.delivery.fooddeliverycustomer.domain.model.search

import com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodItem
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant

data class SearchResult(
    val restaurants: List<Restaurant> = emptyList(),
    val foodItems: List<FoodItem> = emptyList()
)

data class SearchHistory(
    val id: String = "",
    val userId: String = "",
    val query: String = "",
    val createdAt: Long = 0L
)