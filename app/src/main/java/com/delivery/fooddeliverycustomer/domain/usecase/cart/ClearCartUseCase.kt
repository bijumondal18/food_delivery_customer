package com.delivery.fooddeliverycustomer.domain.usecase.cart

import com.delivery.fooddeliverycustomer.data.repository.CartRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke() {
        repository.clearCart()
    }
}