package com.delivery.fooddeliverycustomer.data.model.delivery

import com.delivery.fooddeliverycustomer.data.model.location.Location

data class Delivery(
    val id: String = "",
    val orderId: String = "",

    val deliveryPartnerId: String = "",

    val deliveryPartner: DeliveryPartner? = null,

    val status: DeliveryStatus = DeliveryStatus.ASSIGNED,

    val currentLocation: Location? = null,

    val estimatedDeliveryTime: Long? = null,

    val pickedUpAt: Long? = null,
    val deliveredAt: Long? = null
)

data class DeliveryPartner(
    val id: String = "",

    val name: String = "",
    val phone: String = "",
    val profileImage: String? = null,

    val rating: Double = 0.0,

    val vehicleType: VehicleType = VehicleType.BIKE,

    val vehicleNumber: String = ""
)

enum class DeliveryStatus {
    ASSIGNED,
    ACCEPTED,
    ARRIVED_AT_RESTAURANT,
    PICKED_UP,
    ON_THE_WAY,
    ARRIVED_AT_CUSTOMER,
    DELIVERED
}

enum class VehicleType {
    BIKE,
    SCOOTER,
    CAR,
    BICYCLE
}