package com.delivery.fooddeliverycustomer.domain.usecase.food

import com.delivery.fooddeliverycustomer.data.repository.FoodRepository
import javax.inject.Inject

class RefreshFoodUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        restaurantId: String
    ) {
//        repository.refreshFoodItems(restaurantId)
    }
}