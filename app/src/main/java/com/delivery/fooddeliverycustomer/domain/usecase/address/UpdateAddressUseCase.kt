package com.delivery.fooddeliverycustomer.domain.usecase.address

import com.delivery.fooddeliverycustomer.data.repository.AddressRepository
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import javax.inject.Inject

class UpdateAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(
        address: Address
    ) {
        repository.updateAddress(address)
    }
}