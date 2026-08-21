package com.delivery.fooddeliverycustomer.domain.usecase.address

import com.delivery.fooddeliverycustomer.data.repository.AddressRepository
import javax.inject.Inject

class ObserveAddressesUseCase @Inject constructor(
    private val repository: AddressRepository
) {
    operator fun invoke() =
        repository.observeAddresses()
}