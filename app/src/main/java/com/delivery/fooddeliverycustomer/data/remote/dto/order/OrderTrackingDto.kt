package com.delivery.fooddeliverycustomer.data.remote.dto.order

import com.delivery.fooddeliverycustomer.data.remote.dto.address.LocationDto

data class OrderTrackingDto(
    val orderId: String = "",
    val orderStatus: String? = null,

    val restaurantStatus: String? = null,
    val deliveryStatus: String? = null,

    val estimatedDeliveryTime: String? = null,

    val deliveryPartner: DeliveryPartnerDto? = null,
    val currentLocation: LocationDto? = null,

    val timeline: List<OrderTimelineStepDto> = emptyList()
)