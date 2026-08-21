package com.delivery.fooddeliverycustomer.data.remote.dto.order

import com.delivery.fooddeliverycustomer.data.remote.dto.address.AddressDto

data class OrderDto(
    val id: String,
    val orderNumber: String,
    val userId: String,

    val restaurantId: String,
    val restaurantName: String,
    val restaurantImage: String?,

    val items: List<OrderItemDto>,

    val deliveryAddress: AddressDto,

    val subtotal: Double,
    val deliveryFee: Double,
    val platformFee: Double,
    val tax: Double,
    val discount: Double,
    val totalAmount: Double,

    val paymentMethod: String,
    val paymentStatus: String,

    val orderStatus: String,

    val couponCode: String?,

    val estimatedDeliveryTime: String?,

    val createdAt: String,
    val updatedAt: String
)