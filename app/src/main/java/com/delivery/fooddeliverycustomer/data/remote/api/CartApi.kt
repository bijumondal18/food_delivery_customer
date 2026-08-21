package com.delivery.fooddeliverycustomer.data.remote.api

import com.delivery.fooddeliverycustomer.data.remote.dto.cart.CartDto
import retrofit2.http.Body
import retrofit2.http.POST

interface CartApi {

    @POST("api/v1/cart/sync")
    suspend fun syncCart(
        @Body cart: CartDto
    )
}
