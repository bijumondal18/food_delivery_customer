package com.delivery.fooddeliverycustomer.domain.usecase.restaurant


import com.delivery.fooddeliverycustomer.data.repository.RestaurantRepository
import javax.inject.Inject

class ObserveRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(
        id: String
    ) = repository.observeRestaurant(id)
}