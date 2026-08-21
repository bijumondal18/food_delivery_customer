package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.remote.dto.address.LocationDto


import com.delivery.fooddeliverycustomer.domain.model.location.Location

fun LocationDto.toDomain(): Location {
    return Location(
        latitude = latitude,
        longitude = longitude
    )
}