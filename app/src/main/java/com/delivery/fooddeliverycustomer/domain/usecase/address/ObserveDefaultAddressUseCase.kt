package com.delivery.fooddeliverycustomer.domain.usecase.address

import com.delivery.fooddeliverycustomer.data.repository.AddressRepository
import javax.inject.Inject

class ObserveDefaultAddressUseCase @Inject constructor(
    private val repository: AddressRepository
) {
    operator fun invoke() =
        repository.observeDefaultAddress()
}