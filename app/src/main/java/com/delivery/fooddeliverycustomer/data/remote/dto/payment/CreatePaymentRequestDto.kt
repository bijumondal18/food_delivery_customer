package com.delivery.fooddeliverycustomer.data.remote.dto.payment

data class CreatePaymentRequestDto(
    val orderId: String,
    val amount: Double,
    val paymentMethod: String
)