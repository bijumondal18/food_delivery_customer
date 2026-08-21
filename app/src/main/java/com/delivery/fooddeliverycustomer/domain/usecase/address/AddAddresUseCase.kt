package com.delivery.fooddeliverycustomer.domain.usecase.address

import com.delivery.fooddeliverycustomer.data.repository.AddressRepository
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import javax.inject.Inject

class AddAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(
        address: Address
    ) {
        repository.addAddress(address)
    }
}