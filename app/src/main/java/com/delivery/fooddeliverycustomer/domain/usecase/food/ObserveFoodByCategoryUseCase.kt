package com.delivery.fooddeliverycustomer.domain.usecase.food

import com.delivery.fooddeliverycustomer.data.repository.FoodRepository
import javax.inject.Inject

class ObserveFoodByCategoryUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(
        restaurantId: String,
        categoryId: String,
    ) = repository.observeFoodItemsByCategory(restaurantId, categoryId)
}