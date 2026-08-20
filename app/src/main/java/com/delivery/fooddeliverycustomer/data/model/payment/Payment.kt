package com.delivery.fooddeliverycustomer.data.model.payment

data class Payment(
    val id: String = "",
    val orderId: String = "",

    val amount: Double = 0.0,

    val method: PaymentMethod = PaymentMethod.CASH_ON_DELIVERY,

    val status: PaymentStatus = PaymentStatus.PENDING,

    val transactionId: String? = null,

    val createdAt: Long = 0L
)

enum class PaymentMethod {
    CASH_ON_DELIVERY,
    UPI,
    CARD,
    NET_BANKING,
    WALLET
}

enum class PaymentStatus {
    PENDING,
    PROCESSING,
    SUCCESS,
    FAILED,
    REFUNDED
}