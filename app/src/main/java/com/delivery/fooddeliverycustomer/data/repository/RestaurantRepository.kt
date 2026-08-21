package com.delivery.fooddeliverycustomer.data.repository

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    fun observeRestaurants():
            Flow<Resource<List<Restaurant>>>

    fun observeRestaurant(
        id: String
    ): Flow<Resource<Restaurant?>>

    fun searchRestaurants(
        query: String
    ): Flow<Resource<List<Restaurant>>>

    suspend fun refreshRestaurants()
}