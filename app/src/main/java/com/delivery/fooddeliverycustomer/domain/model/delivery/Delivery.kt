package com.delivery.fooddeliverycustomer.domain.model.delivery

import com.delivery.fooddeliverycustomer.domain.model.location.Location

data class Delivery(
    val id: String = "",
    val orderId: String = "",

    val deliveryPartnerId: String = "",

    val deliveryPartner: com.delivery.fooddeliverycustomer.domain.model.delivery.DeliveryPartner? = null,

    val status: com.delivery.fooddeliverycustomer.domain.model.delivery.DeliveryStatus = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.delivery.DeliveryStatus.ASSIGNED,

    val currentLocation: com.delivery.fooddeliverycustomer.domain.model.location.Location? = null,

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

    val vehicleType: com.delivery.fooddeliverycustomer.domain.model.delivery.VehicleType = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.delivery.VehicleType.BIKE,

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