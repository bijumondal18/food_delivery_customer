package com.delivery.fooddeliverycustomer.domain.usecase.cart

import com.delivery.fooddeliverycustomer.data.repository.CartRepository
import com.delivery.fooddeliverycustomer.domain.model.cart.CartItem
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(
        item: CartItem
    ) {
        repository.addToCart(item)
    }
}