package com.delivery.fooddeliverycustomer.data.model.notification

data class AppNotification(
    val id: String = "",
    val userId: String = "",

    val title: String = "",
    val message: String = "",

    val image: String? = null,

    val type: NotificationType = NotificationType.GENERAL,

    val referenceId: String? = null,

    val isRead: Boolean = false,

    val createdAt: Long = 0L
)

enum class NotificationType {
    GENERAL,
    ORDER,
    PAYMENT,
    OFFER,
    DELIVERY
}