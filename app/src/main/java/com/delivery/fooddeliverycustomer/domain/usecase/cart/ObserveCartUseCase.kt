package com.delivery.fooddeliverycustomer.domain.usecase.cart

import com.delivery.fooddeliverycustomer.data.repository.CartRepository
import javax.inject.Inject

class ObserveCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    operator fun invoke() =
        repository.observeCart()
}