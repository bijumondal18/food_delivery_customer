package com.delivery.fooddeliverycustomer.domain.model.order

data class OrderItem(
    val id: String = "",
    val foodItemId: String = "",

    val name: String = "",
    val image: String? = null,

    val price: Double = 0.0,
    val quantity: Int = 1,

    val customizations: List<com.delivery.fooddeliverycustomer.domain.model.cart.SelectedCustomization> = emptyList(),

    val totalPrice: Double = 0.0
)