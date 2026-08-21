package com.delivery.fooddeliverycustomer.domain.usecase.cart

import com.delivery.fooddeliverycustomer.data.repository.CartRepository
import javax.inject.Inject

class RemoveCartItemUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(
        itemId: String
    ) {
        repository.removeFromCart(itemId)
    }
}