package com.delivery.fooddeliverycustomer.data.model.search

import com.delivery.fooddeliverycustomer.data.model.Restaurant
import com.delivery.fooddeliverycustomer.data.model.restaurant.FoodItem

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