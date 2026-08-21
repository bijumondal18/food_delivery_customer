package com.delivery.fooddeliverycustomer.data.remote.dto.payment

data class CreatePaymentResponseDto(
    val paymentId: String,
    val orderId: String,
    val amount: Double,
    val currency: String,
    val paymentGateway: String,
    val gatewayOrderId: String?,
    val clientSecret: String?
)