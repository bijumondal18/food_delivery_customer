package com.delivery.fooddeliverycustomer.domain.usecase.food

import com.delivery.fooddeliverycustomer.data.repository.FoodRepository
import javax.inject.Inject

class ObserveFoodItemUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(
        foodItemId: String
    ) = repository.observeFoodItem(foodItemId)
}