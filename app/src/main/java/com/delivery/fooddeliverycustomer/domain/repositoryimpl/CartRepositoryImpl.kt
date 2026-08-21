package com.delivery.fooddeliverycustomer.domain.repositoryimpl

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.data.local.dao.CartDao
import com.delivery.fooddeliverycustomer.data.mapper.*
import com.delivery.fooddeliverycustomer.data.remote.api.CartApi
import com.delivery.fooddeliverycustomer.data.repository.AuthRepository
import com.delivery.fooddeliverycustomer.data.repository.CartRepository
import com.delivery.fooddeliverycustomer.domain.model.cart.Cart
import com.delivery.fooddeliverycustomer.domain.model.cart.CartItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dao: CartDao,
    private val api: CartApi,
    private val authRepository: AuthRepository
) : CartRepository {

    override fun observeCart():
            Flow<Resource<Cart?>> {

        val userId = authRepository.getCurrentUser()?.uid
            ?: return flowOf(Resource.Success(null))

        return dao.observeCart(userId)
            .map { entity ->
                Resource.Success(
                    entity?.toDomain()
                )
            }
    }

    override suspend fun addToCart(
        item: CartItem
    ) {
        dao.insertCartItem(
            item.toEntity()
        )
    }

    override suspend fun updateCartItem(
        itemId: String,
        quantity: Int
    ) {
        val userId = authRepository.getCurrentUser()?.uid ?: return
        val cart = dao.getCart(userId) ?: return
        val cartItem = dao.getCartItem(cart.id, itemId) ?: return

        val newTotalPrice = cartItem.basePrice * quantity

        dao.updateQuantity(
            cart.id,
            itemId,
            quantity,
            newTotalPrice
        )
    }

    override suspend fun removeFromCart(
        itemId: String
    ) {
        val userId = authRepository.getCurrentUser()?.uid ?: return
        val cart = dao.getCart(userId) ?: return
        dao.deleteCartItem(cart.id, itemId)
    }

    override suspend fun clearCart() {
        val userId = authRepository.getCurrentUser()?.uid ?: return
        dao.clearCart(userId)
    }

    override suspend fun syncCart() {

        val userId = authRepository.getCurrentUser()?.uid ?: return
        val cartEntity = dao.getCart(userId)

        if (cartEntity != null) {
            val cart = cartEntity.toDomain()
            api.syncCart(
                cart.toDto()
            )
        }
    }
}