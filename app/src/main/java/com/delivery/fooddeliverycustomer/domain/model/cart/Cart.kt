package com.delivery.fooddeliverycustomer.domain.model.cart

import com.delivery.fooddeliverycustomer.domain.model.coupon.Coupon

data class Cart(
    val id: String = "",
    val userId: String = "",
    val restaurantId: String = "",

    val restaurantName: String = "",
    val restaurantImage: String? = null,

    val items: List<com.delivery.fooddeliverycustomer.domain.model.cart.CartItem> = emptyList(),

    val subtotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val platformFee: Double = 0.0,
    val tax: Double = 0.0,
    val discount: Double = 0.0,

    val totalAmount: Double = 0.0,

    val coupon: com.delivery.fooddeliverycustomer.domain.model.coupon.Coupon? = null
)

data class CartItem(
    val id: String = "",
    val foodItemId: String = "",
    val name: String = "",
    val image: String? = null,

    val basePrice: Double = 0.0,
    val finalPrice: Double = 0.0,

    val quantity: Int = 1,

    val customizations: List<com.delivery.fooddeliverycustomer.domain.model.cart.SelectedCustomization> = emptyList(),

    val totalPrice: Double = 0.0
)

data class SelectedCustomization(
    val groupId: String = "",
    val groupName: String = "",

    val options: List<com.delivery.fooddeliverycustomer.domain.model.cart.SelectedCustomizationOption> = emptyList()
)

data class SelectedCustomizationOption(
    val optionId: String = "",
    val name: String = "",
    val price: Double = 0.0
)