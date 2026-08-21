package com.delivery.fooddeliverycustomer.domain.usecase.food

import com.delivery.fooddeliverycustomer.data.repository.FoodRepository
import javax.inject.Inject

class SearchFoodUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(
        restaurantId: String,
        query: String
    ) = repository.searchFoodItems(
        restaurantId,
        query
    )
}