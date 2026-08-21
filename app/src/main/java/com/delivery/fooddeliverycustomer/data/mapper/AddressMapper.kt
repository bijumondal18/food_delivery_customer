package com.delivery.fooddeliverycustomer.data.mapper


import com.delivery.fooddeliverycustomer.data.local.entity.AddressEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.address.AddressDto
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import com.delivery.fooddeliverycustomer.domain.model.location.AddressLabel

fun AddressDto.toEntity(): AddressEntity {
    return AddressEntity(
        id = id,
        userId = userId,
        label = label,
        name = name,
        phone = phone,
        addressLine1 = addressLine1,
        addressLine2 = addressLine2 ?: "",
        landmark = landmark ?: "",
        city = city,
        state = state,
        country = country,
        postalCode = postalCode,
        latitude = latitude,
        longitude = longitude,
        isDefault = isDefault
    )
}

fun AddressDto.toDomain(): Address {
    return Address(
        id = id,
        userId = userId,
        label = AddressLabel.entries.find { it.name.equals(label, ignoreCase = true) } ?: AddressLabel.OTHER,
        name = name,
        phone = phone,
        addressLine1 = addressLine1,
        addressLine2 = addressLine2 ?: "",
        landmark = landmark ?: "",
        city = city,
        state = state,
        country = country,
        postalCode = postalCode,
        latitude = latitude,
        longitude = longitude,
        isDefault = isDefault
    )
}

fun AddressEntity.toDomain(): Address {
    return Address(
        id = id,
        userId = userId,
        label = AddressLabel.entries.find { it.name.equals(label, ignoreCase = true) } ?: AddressLabel.OTHER,
        name = name,
        phone = phone,
        addressLine1 = addressLine1,
        addressLine2 = addressLine2,
        landmark = landmark,
        city = city,
        state = state,
        country = country,
        postalCode = postalCode,
        latitude = latitude,
        longitude = longitude,
        isDefault = isDefault
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        id = id,
        userId = userId,
        label = label.name,
        name = name,
        phone = phone,
        addressLine1 = addressLine1,
        addressLine2 = addressLine2,
        landmark = landmark,
        city = city,
        state = state,
        country = country,
        postalCode = postalCode,
        latitude = latitude,
        longitude = longitude,
        isDefault = isDefault
    )
}

fun Address.toDto(): AddressDto {
    return AddressDto(
        id = id,
        userId = userId,
        label = label.name,
        name = name,
        phone = phone,
        addressLine1 = addressLine1,
        addressLine2 = addressLine2,
        landmark = landmark,
        city = city,
        state = state,
        country = country,
        postalCode = postalCode,
        latitude = latitude,
        longitude = longitude,
        isDefault = isDefault
    )
}
