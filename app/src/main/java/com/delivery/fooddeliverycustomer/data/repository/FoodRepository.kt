package com.delivery.fooddeliverycustomer.data.repository

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodItem
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    fun observeFoodItems(
        restaurantId: String
    ): Flow<Resource<List<FoodItem>>>

    fun observeFoodItem(
        foodItemId: String
    ): Flow<Resource<FoodItem?>>

    fun observeFoodItemsByCategory(
        restaurantId: String,
        categoryId: String
    ): Flow<Resource<List<FoodItem>>>

    fun searchFoodItems(
        restaurantId: String,
        query: String
    ): Flow<Resource<List<FoodItem>>>

//    suspend fun refreshFoodItems(
//        restaurantId: String
//    )
}