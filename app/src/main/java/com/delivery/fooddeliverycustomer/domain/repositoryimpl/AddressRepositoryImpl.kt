package com.delivery.fooddeliverycustomer.domain.repositoryimpl

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.data.local.dao.AddressDao
import com.delivery.fooddeliverycustomer.data.mapper.toCreateRequest
import com.delivery.fooddeliverycustomer.data.mapper.toDomain
import com.delivery.fooddeliverycustomer.data.mapper.toEntity
import com.delivery.fooddeliverycustomer.data.mapper.toUpdateRequest
import com.delivery.fooddeliverycustomer.data.remote.api.AddressApi
import com.delivery.fooddeliverycustomer.data.repository.AddressRepository
import com.delivery.fooddeliverycustomer.data.repository.AuthRepository
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val dao: AddressDao,
    private val api: AddressApi,
    private val authRepository: AuthRepository
) : AddressRepository {

    override fun observeAddresses():
            Flow<Resource<List<Address>>> {
        val userId = authRepository.getCurrentUser()?.uid ?: ""
        return dao.observeAddresses(userId)
            .map { entities ->
                Resource.Success(
                    entities.map { it.toDomain() }
                )
            }
    }

    override fun observeDefaultAddress():
            Flow<Resource<Address?>> {
        val userId = authRepository.getCurrentUser()?.uid ?: ""
        return dao.observeDefaultAddress(userId)
            .map {
                Resource.Success(
                    it?.toDomain()
                )
            }
    }

    override suspend fun addAddress(
        address: Address
    ) {

        api.createAddress(
            address.toCreateRequest()
        )

        refreshAddresses()
    }

    override suspend fun updateAddress(
        address: Address
    ) {

        api.updateAddress(
            address.id,
            address.toUpdateRequest()
        )

        refreshAddresses()
    }

    override suspend fun deleteAddress(
        id: String
    ) {

        api.deleteAddress(id)

        dao.deleteAddress(id)
    }

    override suspend fun setDefaultAddress(
        id: String
    ) {
        val userId = authRepository.getCurrentUser()?.uid ?: ""

        api.setDefaultAddress(id)

        dao.clearDefaultAddress(userId)
        dao.setDefaultAddress(id)
    }

    override suspend fun refreshAddresses() {
        val userId = authRepository.getCurrentUser()?.uid ?: ""

        val response = api.getAddresses()

        dao.replaceAddresses(
            userId,
            response.map { it.toEntity() }
        )
    }
}