package com.delivery.fooddeliverycustomer.domain.usecase.food

import com.delivery.fooddeliverycustomer.data.repository.FoodRepository
import javax.inject.Inject

class ObserveFoodItemsUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(
        restaurantId: String
    ) = repository.observeFoodItems(restaurantId)
}