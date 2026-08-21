package com.delivery.fooddeliverycustomer.data.repository

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {

    fun observeAddresses():
            Flow<Resource<List<Address>>>

    fun observeDefaultAddress():
            Flow<Resource<Address?>>

    suspend fun addAddress(
        address: Address
    )

    suspend fun updateAddress(
        address: Address
    )

    suspend fun deleteAddress(
        id: String
    )

    suspend fun setDefaultAddress(
        id: String
    )

    suspend fun refreshAddresses()
}