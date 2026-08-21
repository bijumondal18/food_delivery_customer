package com.delivery.fooddeliverycustomer.data.remote.api

import com.delivery.fooddeliverycustomer.data.remote.dto.address.AddressDto
import com.delivery.fooddeliverycustomer.data.remote.dto.address.CreateAddressRequestDto
import com.delivery.fooddeliverycustomer.data.remote.dto.address.UpdateAddressRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AddressApi {

    @GET("addresses")
    suspend fun getAddresses(): List<AddressDto>

    @POST("addresses")
    suspend fun createAddress(
        @Body request: CreateAddressRequestDto
    ): AddressDto

    @PUT("addresses/{id}")
    suspend fun updateAddress(
        @Path("id") id: String,
        @Body request: UpdateAddressRequestDto
    ): AddressDto

    @DELETE("addresses/{id}")
    suspend fun deleteAddress(
        @Path("id") id: String
    )

    @POST("addresses/{id}/default")
    suspend fun setDefaultAddress(
        @Path("id") id: String
    )
}