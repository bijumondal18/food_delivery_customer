package com.delivery.fooddeliverycustomer.data.remote.api

import com.delivery.fooddeliverycustomer.data.remote.dto.order.CreateOrderRequestDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderTrackingDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @GET("orders")
    suspend fun getOrders(): List<OrderDto>

    @POST("orders")
    suspend fun createOrder(
        @Body request: CreateOrderRequestDto
    ): OrderDto

    @DELETE("orders/{orderId}")
    suspend fun cancelOrder(
        @Path("orderId") orderId: String
    )

    @GET("orders/{orderId}/tracking")
    fun observeOrderTracking(
        @Path("orderId") orderId: String
    ): OrderTrackingDto

}