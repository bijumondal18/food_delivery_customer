package com.delivery.fooddeliverycustomer.domain.model.order

import com.delivery.fooddeliverycustomer.domain.model.delivery.DeliveryPartner
import com.delivery.fooddeliverycustomer.domain.model.location.Location

data class OrderTracking(
    val orderId: String,
    val orderStatus: OrderStatus?,
    val restaurantStatus: String?,
    val deliveryStatus: String?,
    val estimatedDeliveryTime: String?,
    val deliveryPartner: DeliveryPartner?,
    val currentLocation: Location?,
    val timeline: List<OrderTimelineStep>
)

data class OrderTimelineStep(
    val status: String,
    val title: String,
    val description: String?,
    val timestamp: String?,
    val completed: Boolean
)
