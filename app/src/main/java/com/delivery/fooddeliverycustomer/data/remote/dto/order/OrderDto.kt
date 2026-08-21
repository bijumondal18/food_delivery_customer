package com.delivery.fooddeliverycustomer.data.remote.dto.order

import com.delivery.fooddeliverycustomer.data.remote.dto.address.AddressDto

data class OrderDto(
    val id: String = "",
    val orderNumber: String = "",

    val userId: String = "",
    val restaurantId: String = "",

    val restaurantName: String = "",
    val restaurantImage: String? = null,

    val items: List<OrderItemDto> = emptyList(),

    val deliveryAddress: AddressDto? = null,

    val subtotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val platformFee: Double = 0.0,
    val tax: Double = 0.0,
    val discount: Double = 0.0,

    val totalAmount: Double = 0.0,

    val paymentMethod: String = "CASH_ON_DELIVERY",
    val paymentStatus: String = "PENDING",

    val orderStatus: String = "PLACED",

    val couponCode: String? = null,

    val estimatedDeliveryTime: Long? = null,

    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)