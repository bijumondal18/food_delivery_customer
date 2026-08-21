package com.delivery.fooddeliverycustomer.data.repository

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.domain.model.cart.Cart
import com.delivery.fooddeliverycustomer.domain.model.cart.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun observeCart(): Flow<Resource<Cart?>>

    suspend fun addToCart(
        item: CartItem
    )

    suspend fun updateCartItem(
        itemId: String,
        quantity: Int
    )

    suspend fun removeFromCart(
        itemId: String
    )

    suspend fun clearCart()

    suspend fun syncCart()
}