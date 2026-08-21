package com.delivery.fooddeliverycustomer.domain.usecase.restaurant

import com.delivery.fooddeliverycustomer.data.repository.RestaurantRepository
import javax.inject.Inject

class SearchRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(
        query: String
    ) = repository.searchRestaurants(query)
}