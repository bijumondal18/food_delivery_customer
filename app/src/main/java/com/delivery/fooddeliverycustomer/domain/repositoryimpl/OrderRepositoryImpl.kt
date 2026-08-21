package com.delivery.fooddeliverycustomer.domain.repositoryimpl

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.data.local.dao.OrderDao
import com.delivery.fooddeliverycustomer.data.mapper.toDomain
import com.delivery.fooddeliverycustomer.data.mapper.toDto
import com.delivery.fooddeliverycustomer.data.mapper.toEntity
import com.delivery.fooddeliverycustomer.data.remote.api.OrderApi
import com.delivery.fooddeliverycustomer.data.repository.AuthRepository
import com.delivery.fooddeliverycustomer.data.repository.OrderRepository
import com.delivery.fooddeliverycustomer.domain.model.order.CreateOrderRequest
import com.delivery.fooddeliverycustomer.domain.model.order.Order
import com.delivery.fooddeliverycustomer.domain.model.order.OrderTracking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val dao: OrderDao,
    private val api: OrderApi,
    private val authRepository: AuthRepository
) : OrderRepository {

    override fun observeOrders(
        userId: String
    ): Flow<Resource<List<Order>>> {

        return dao.observeOrders(userId)
            .map { entities ->
                Resource.Success(
                    entities.map { it.toDomain() }
                )
            }
    }

    override fun observeOrder(
        orderId: String
    ): Flow<Resource<Order?>> {

        return dao.observeOrder(orderId)
            .map {
                Resource.Success(
                    it?.toDomain()
                )
            }
    }

    override fun observeOrderTracking(
        orderId: String
    ): Flow<Resource<OrderTracking?>> = flow {

        try {
            val response = api.observeOrderTracking(orderId)

            emit(
                Resource.Success(
                    response.toDomain()
                )
            )

        } catch (e: Exception) {

            emit(
                Resource.Error(
                    e.message ?: "Unable to get order tracking"
                )
            )
        }
    }

    override suspend fun createOrder(
        request: CreateOrderRequest
    ): Resource<Order> {

        return try {

            val response =
                api.createOrder(
                    request.toDto()
                )

            dao.insertOrder(
                response.toEntity()
            )

            Resource.Success(
                response.toDomain()
            )

        } catch (e: Exception) {

            Resource.Error(
                e.message ?: "Unable to create order"
            )
        }
    }

    override suspend fun cancelOrder(
        orderId: String
    ): Resource<Boolean> {

        return try {

            api.cancelOrder(orderId)

            Resource.Success(true)

        } catch (e: Exception) {

            Resource.Error(
                e.message ?: "Unable to cancel order"
            )
        }
    }

    override suspend fun refreshOrders() {

        try {
            val response =
                api.getOrders()

            dao.replaceOrders(
                response.map { it.toEntity() }
            )
        } catch (e: Exception) {
            // Log error or handle silently for background sync
        }
    }
}