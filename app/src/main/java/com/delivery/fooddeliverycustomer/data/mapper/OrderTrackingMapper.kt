package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.local.entity.OrderTrackingEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.address.LocationDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.DeliveryPartnerDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderTrackingDto
import com.delivery.fooddeliverycustomer.domain.model.order.OrderTracking
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val trackingGson = Gson()

fun OrderTrackingDto.toDomain(): OrderTracking = toEntity().toDomain()

fun OrderTrackingDto.toEntity(): OrderTrackingEntity {
    return OrderTrackingEntity(
        orderId = orderId,

        orderStatus = orderStatus,

        restaurantStatus = restaurantStatus,

        deliveryStatus = deliveryStatus,

        estimatedDeliveryTime = estimatedDeliveryTime,

        deliveryPartnerJson = deliveryPartner?.let {
            trackingGson.toJson(it)
        },

        currentLocationJson = currentLocation?.let {
            trackingGson.toJson(it)
        },

        timelineJson = trackingGson.toJson(timeline)
    )
}

fun OrderTrackingEntity.toDomain(): OrderTracking {

    val timelineType =
        object : TypeToken<List<com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderTimelineStepDto>>() {}.type

    val timelineDtos:
            List<com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderTimelineStepDto> =
        trackingGson.fromJson(
            timelineJson,
            timelineType
        )

    return OrderTracking(
        orderId = orderId,

        orderStatus = orderStatus?.let {
            runCatching {
                com.delivery.fooddeliverycustomer.domain.model.order.OrderStatus.valueOf(it)
            }.getOrNull()
        },

        restaurantStatus = restaurantStatus,

        deliveryStatus = deliveryStatus,

        estimatedDeliveryTime = estimatedDeliveryTime,

        deliveryPartner = deliveryPartnerJson?.let {
            trackingGson.fromJson(
                it,
                DeliveryPartnerDto::class.java
            ).toDomain()
        },

        currentLocation = currentLocationJson?.let {
            trackingGson.fromJson(
                it,
                LocationDto::class.java
            ).toDomain()
        },

        timeline = timelineDtos.map {
            it.toDomain()
        }
    )
}