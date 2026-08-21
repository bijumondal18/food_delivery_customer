package com.delivery.fooddeliverycustomer.domain.model.restaurant

import com.delivery.fooddeliverycustomer.domain.model.location.Address


data class Restaurant(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val images: List<String> = emptyList(),
    val logo: String? = null,

    val cuisines: List<String> = emptyList(),
    val rating: Double = 0.0,
    val totalRatings: Int = 0,

    val deliveryTime: Int = 0, // minutes
    val deliveryFee: Double = 0.0,
    val minimumOrderAmount: Double = 0.0,

    val isOpen: Boolean = false,
    val isPureVeg: Boolean = false,
    val isPromoted: Boolean = false,

    val address: Address? = null,

    val categories: List<FoodCategory> = emptyList(),

    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)

data class Offer(
    val title: String,
    val description: String? = null,
    val discountType: DiscountType,
    val discountValue: Double,
    val maximumDiscount: Double? = null,
    val minimumOrderAmount: Double? = null
)

enum class DiscountType {
    PERCENTAGE,
    FIXED_AMOUNT
}

data class FoodCategory(
    val id: String = "",
    val restaurantId: String = "",
    val name: String = "",
    val description: String = "",
    val image: String? = null,
    val sortOrder: Int = 0,
    val isActive: Boolean = true
)

data class FoodItem(
    val id: String = "",
    val restaurantId: String = "",
    val categoryId: String = "",

    val name: String = "",
    val description: String = "",
    val image: String? = null,

    val price: Double = 0.0,
    val discountedPrice: Double? = null,

    val isVeg: Boolean = false,
    val isEgg: Boolean = false,
    val isAvailable: Boolean = true,
    val isBestSeller: Boolean = false,

    val rating: Double = 0.0,
    val totalRatings: Int = 0,

    val customizationGroups: List<com.delivery.fooddeliverycustomer.domain.model.restaurant.CustomizationGroup> = emptyList(),

    val tags: List<String> = emptyList(),

    val preparationTime: Int = 0
)

data class CustomizationGroup(
    val id: String = "",
    val name: String = "",
    val type: com.delivery.fooddeliverycustomer.domain.model.restaurant.CustomizationType = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.restaurant.CustomizationType.SINGLE,
    val isRequired: Boolean = false,
    val minSelection: Int = 0,
    val maxSelection: Int = 1,
    val options: List<com.delivery.fooddeliverycustomer.domain.model.restaurant.CustomizationOption> = emptyList()
)

data class CustomizationOption(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val isAvailable: Boolean = true
)

enum class CustomizationType {
    SINGLE,
    MULTIPLE
}