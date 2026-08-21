package com.delivery.fooddeliverycustomer.domain.model.payment

data class Payment(
    val id: String = "",
    val orderId: String = "",

    val amount: Double = 0.0,

    val method: com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod.CASH_ON_DELIVERY,

    val status: com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus.PENDING,

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