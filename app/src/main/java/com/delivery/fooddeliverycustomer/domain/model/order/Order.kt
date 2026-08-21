package com.delivery.fooddeliverycustomer.domain.model.order

import com.delivery.fooddeliverycustomer.domain.model.cart.SelectedCustomization
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod
import com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus

enum class OrderStatus {
    PLACED,
    CONFIRMED,
    RESTAURANT_PREPARING,
    READY_FOR_PICKUP,
    PICKED_UP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED,
    REFUNDED
}

data class Order(
    val id: String = "",
    val orderNumber: String = "",

    val userId: String = "",
    val restaurantId: String = "",

    val restaurantName: String = "",
    val restaurantImage: String? = null,

    val items: List<OrderItem> = emptyList(),

    val deliveryAddress: Address? = null,

    val subtotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val platformFee: Double = 0.0,
    val tax: Double = 0.0,
    val discount: Double = 0.0,

    val totalAmount: Double = 0.0,

    val paymentMethod: com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod.CASH_ON_DELIVERY,
    val paymentStatus: com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus.PENDING,

    val orderStatus: com.delivery.fooddeliverycustomer.domain.model.order.OrderStatus = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.order.OrderStatus.PLACED,

    val couponCode: String? = null,

    val estimatedDeliveryTime: Long? = null,

    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)
