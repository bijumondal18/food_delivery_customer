package com.delivery.fooddeliverycustomer.data.remote.dto.payment

data class PaymentStatusDto(
    val paymentId: String,
    val orderId: String,
    val status: String,
    val amount: Double,
    val currency: String,
    val transactionId: String?,
    val paidAt: String?
)