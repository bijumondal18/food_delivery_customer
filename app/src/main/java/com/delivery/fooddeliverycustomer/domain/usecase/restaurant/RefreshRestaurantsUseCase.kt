package com.delivery.fooddeliverycustomer.domain.usecase.restaurant

import com.delivery.fooddeliverycustomer.data.repository.RestaurantRepository
import javax.inject.Inject

class RefreshRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke() {
        repository.refreshRestaurants()
    }
}