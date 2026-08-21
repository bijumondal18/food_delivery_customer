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

    val distance: Double = 0.0,
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


object RestaurantMockData {

    val recommendedRestaurants = listOf(
        Restaurant(
            id = "restaurant_001",
            name = "Momo I Am",
            description = "Authentic momos, noodles and delicious Asian street food.",
            images = listOf(
                "https://images.unsplash.com/photo-1625220194771-7ebdea0b70b9",
                "https://images.unsplash.com/photo-1563245372-f21724e3856d"
            ),
            logo = "https://images.unsplash.com/photo-1552566626-52f8b828add9",
            cuisines = listOf("Momos", "Chinese", "Asian"),
            rating = 4.7,
            totalRatings = 2840,
            distance = 1.2,
            deliveryTime = 28,
            deliveryFee = 29.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = true,
            address = null,
            categories = emptyList(),
            createdAt = 1720000000000L,
            updatedAt = 1755000000000L
        ),

        Restaurant(
            id = "restaurant_002",
            name = "Kolkata Biryani House",
            description = "Traditional Kolkata-style biryani with authentic flavors.",
            images = listOf(
                "https://images.unsplash.com/photo-1563379091339-03246963d51a",
                "https://images.unsplash.com/photo-1589302168068-964664d93dc0"
            ),
            logo = "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f",
            cuisines = listOf("Biryani", "Mughlai", "Indian"),
            rating = 4.6,
            totalRatings = 4210,
            distance = 1.5,
            deliveryTime = 32,
            deliveryFee = 35.0,
            minimumOrderAmount = 199.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = true,
            address = null,
            categories = emptyList(),
            createdAt = 1720000000000L,
            updatedAt = 1755000000000L
        ),

        Restaurant(
            id = "restaurant_003",
            name = "The Burger Factory",
            description = "Juicy burgers, crispy fries and loaded sides.",
            images = listOf(
                "https://images.unsplash.com/photo-1568901346375-23c9450c58cd",
                "https://images.unsplash.com/photo-1550547660-d9450f859349"
            ),
            logo = "https://images.unsplash.com/photo-1571091718767-18b5b1457add",
            cuisines = listOf("Burgers", "Fast Food", "American"),
            rating = 4.5,
            totalRatings = 1980,
            distance = 1.8,
            deliveryTime = 25,
            deliveryFee = 25.0,
            minimumOrderAmount = 129.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = true,
            address = null,
            categories = emptyList(),
            createdAt = 1720000000000L,
            updatedAt = 1755000000000L
        ),

        Restaurant(
            id = "restaurant_004",
            name = "Green Leaf Kitchen",
            description = "Healthy vegetarian meals prepared with fresh ingredients.",
            images = listOf(
                "https://images.unsplash.com/photo-1512621776951-a57141f2eefd",
                "https://images.unsplash.com/photo-1540420773420-3366772f4999"
            ),
            logo = "https://images.unsplash.com/photo-1547592180-85f173990554",
            cuisines = listOf("North Indian", "Healthy Food", "Vegetarian"),
            rating = 4.8,
            totalRatings = 1560,
            distance = 2.4,
            deliveryTime = 22,
            deliveryFee = 19.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = true,
            isPromoted = false,
            address = null,
            categories = emptyList(),
            createdAt = 1720000000000L,
            updatedAt = 1755000000000L
        )
    )

    val nearbyRestaurants = listOf(
        Restaurant(
            id = "restaurant_005",
            name = "Bengal Kitchen",
            description = "Homestyle Bengali food with authentic traditional recipes.",
            images = listOf(
                "https://images.unsplash.com/photo-1601050690597-df0568f70950",
                "https://images.unsplash.com/photo-1540420773420-3366772f4999"
            ),
            logo = "https://images.unsplash.com/photo-1547592180-85f173990554",
            cuisines = listOf("Bengali", "Indian", "Thali"),
            rating = 4.4,
            totalRatings = 980,
            distance = 1.2,
            deliveryTime = 20,
            deliveryFee = 15.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = false,
            address = null,
            categories = emptyList()
        ),

        Restaurant(
            id = "restaurant_006",
            name = "Rolls & More",
            description = "Kolkata-style rolls, wraps and quick bites.",
            images = listOf(
                "https://images.unsplash.com/photo-1626700051175-6818013e1d4f"
            ),
            logo = "https://images.unsplash.com/photo-1565299507177-b0ac66763828",
            cuisines = listOf("Rolls", "Fast Food", "Street Food"),
            rating = 4.3,
            totalRatings = 760,
            distance = 1.5,
            deliveryTime = 18,
            deliveryFee = 19.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = false,
            address = null,
            categories = emptyList()
        ),

        Restaurant(
            id = "restaurant_007",
            name = "Chai & Adda",
            description = "Tea, coffee, snacks and everything you need for an evening adda.",
            images = listOf(
                "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb"
            ),
            logo = "https://images.unsplash.com/photo-1544787219-7f47ccb76574",
            cuisines = listOf("Cafe", "Tea", "Snacks"),
            rating = 4.5,
            totalRatings = 1120,
            distance = 1.9,
            deliveryTime = 15,
            deliveryFee = 10.0,
            minimumOrderAmount = 79.0,
            isOpen = true,
            isPureVeg = true,
            isPromoted = false,
            address = null,
            categories = emptyList()
        ),

        Restaurant(
            id = "restaurant_008",
            name = "Spice Route",
            description = "Flavourful Indian and Mughlai dishes for every occasion.",
            images = listOf(
                "https://images.unsplash.com/photo-1601050690117-94f5f6fa8bd7"
            ),
            logo = "https://images.unsplash.com/photo-1552566626-52f8b828add9",
            cuisines = listOf("Indian", "Mughlai", "North Indian"),
            rating = 4.2,
            totalRatings = 640,
            distance = 2.4,
            deliveryTime = 27,
            deliveryFee = 25.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = false,
            categories = emptyList()
        ),

        Restaurant(
            id = "restaurant_009",
            name = "Pizza Corner",
            description = "Freshly baked pizzas with crispy crusts and loaded toppings.",
            images = listOf(
                "https://images.unsplash.com/photo-1574071318508-1cdbab80d002"
            ),
            logo = "https://images.unsplash.com/photo-1579751626657-72bc17010498",
            cuisines = listOf("Pizza", "Italian", "Fast Food"),
            rating = 4.1,
            totalRatings = 890,
            deliveryTime = 30,
            deliveryFee = 29.0,
            minimumOrderAmount = 199.0,
            isOpen = true,
            isPureVeg = false,
            isPromoted = false,
            categories = emptyList()
        ),

        Restaurant(
            id = "restaurant_010",
            name = "Dosa Junction",
            description = "South Indian breakfast, dosas, idli and authentic filter coffee.",
            images = listOf(
                "https://images.unsplash.com/photo-1668236543090-82eba5ee5976"
            ),
            logo = "https://images.unsplash.com/photo-1630383249896-424e482df921",
            cuisines = listOf("South Indian", "Dosa", "Breakfast"),
            rating = 4.6,
            totalRatings = 1340,
            deliveryTime = 21,
            deliveryFee = 15.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = true,
            isPromoted = false,
            categories = emptyList()
        )
    )
}