package com.delivery.fooddeliverycustomer.domain.usecase.address

import com.delivery.fooddeliverycustomer.data.repository.AddressRepository
import javax.inject.Inject

class DeleteAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(
        id: String
    ) {
        repository.deleteAddress(id)
    }
}