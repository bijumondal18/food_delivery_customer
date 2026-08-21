package com.delivery.fooddeliverycustomer.data.remote.dto.order

import com.delivery.fooddeliverycustomer.data.remote.dto.address.LocationDto

data class OrderTrackingDto(
    val orderId: String,
    val orderStatus: String,
    val restaurantStatus: String?,
    val deliveryStatus: String?,
    val estimatedDeliveryTime: String?,
    val deliveryPartner: DeliveryPartnerDto?,
    val currentLocation: LocationDto?,
    val timeline: List<OrderStatusDto>
)