package com.delivery.fooddeliverycustomer.data.repository

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.domain.model.order.CreateOrderRequest
import com.delivery.fooddeliverycustomer.domain.model.order.Order
import com.delivery.fooddeliverycustomer.domain.model.order.OrderTracking
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun observeOrders(
        userId: String
    ): Flow<Resource<List<Order>>>

    fun observeOrder(
        orderId: String
    ): Flow<Resource<Order?>>

    fun observeOrderTracking(
        orderId: String
    ): Flow<Resource<OrderTracking?>>

    suspend fun createOrder(
        request: CreateOrderRequest
    ): Resource<Order>

    suspend fun cancelOrder(
        orderId: String
    ): Resource<Boolean>

    suspend fun refreshOrders()
}