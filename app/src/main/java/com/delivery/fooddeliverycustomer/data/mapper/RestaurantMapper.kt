package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.local.entity.RestaurantEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.restaurant.RestaurantDto
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant

fun RestaurantDto.toEntity(): RestaurantEntity {

    return RestaurantEntity(
        id = id,
        name = name,
        description = description.orEmpty(),
        logo = logo,
        images = images
            .sortedBy { it.sortOrder }
            .map { it.url },
        cuisines = cuisines,
        rating = rating,
        totalRatings = totalRatings,
        deliveryTime = deliveryTime,
        deliveryFee = deliveryFee,
        minimumOrderAmount =
            minimumOrderAmount,
        isOpen = isOpen,
        isPureVeg = isPureVeg,
        isPromoted = isPromoted,
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
        addressLine =
            address?.addressLine1.orEmpty(),
        city =
            address?.city.orEmpty(),
        state =
            address?.state.orEmpty(),
        postalCode =
            address?.postalCode.orEmpty(),
        updatedAt =
            System.currentTimeMillis()
    )
}

fun RestaurantEntity.toDomain(): Restaurant {

    return Restaurant(
        id = id,
        name = name,
        description = description.orEmpty(),
        logo = logo,
        images = images,
        cuisines = cuisines,
        rating = rating,
        totalRatings = totalRatings,
        deliveryTime = deliveryTime,
        deliveryFee = deliveryFee,
        minimumOrderAmount =
            minimumOrderAmount,
        isOpen = isOpen,
        isPureVeg = isPureVeg,
        isPromoted = isPromoted
    )
}