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
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_006",
            name = "Rolls & More",
            description = "Kolkata-style rolls, wraps and quick bites.",
            images = listOf(
                "https://images.unsplash.com/photo-1626700051175-6818013e1d4f",
                "https://images.unsplash.com/photo-1540420773420-3366772f4999"
            ),
            logo = "https://images.unsplash.com/photo-1565299507177-b0ac66763828",
            cuisines = listOf("Rolls", "Fast Food", "Street Food"),
            rating = 4.3,
            totalRatings = 760,
            distance = 0.8,
            deliveryTime = 18,
            deliveryFee = 19.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = false
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
            distance = 0.5,
            deliveryTime = 15,
            deliveryFee = 10.0,
            minimumOrderAmount = 79.0,
            isOpen = true,
            isPureVeg = true
        ),

        Restaurant(
            id = "restaurant_008",
            name = "Spice Route",
            description = "Flavourful Indian and Mughlai dishes for every occasion.",
            images = listOf(
                "https://images.unsplash.com/photo-1601050690117-94f5f6fa8bd7",
                "https://images.unsplash.com/photo-1540420773420-3366772f4999"
            ),
            logo = "https://images.unsplash.com/photo-1552566626-52f8b828add9",
            cuisines = listOf("Indian", "Mughlai", "North Indian"),
            rating = 4.2,
            totalRatings = 640,
            distance = 2.1,
            deliveryTime = 27,
            deliveryFee = 25.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false
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
            distance = 1.7,
            deliveryTime = 30,
            deliveryFee = 29.0,
            minimumOrderAmount = 199.0,
            isOpen = true,
            isPureVeg = false
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
            distance = 1.0,
            deliveryTime = 21,
            deliveryFee = 15.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = true
        ),

        Restaurant(
            id = "restaurant_011",
            name = "Mughal Darbar",
            description = "Royal Mughlai dishes, kebabs and aromatic biryanis.",
            images = listOf(
                "https://images.unsplash.com/photo-1545247181-516773cae754"
            ),
            logo = "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f",
            cuisines = listOf("Mughlai", "Biryani", "Kebab"),
            rating = 4.5,
            totalRatings = 2180,
            distance = 2.4,
            deliveryTime = 35,
            deliveryFee = 35.0,
            minimumOrderAmount = 199.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_012",
            name = "Urban Tadka",
            description = "Modern Indian cuisine with classic flavours.",
            images = listOf(
                "https://images.unsplash.com/photo-1585937421612-70a008356fbe"
            ),
            logo = "https://images.unsplash.com/photo-1569058242253-92a9c755a0ec",
            cuisines = listOf("North Indian", "Punjabi", "Indian"),
            rating = 4.3,
            totalRatings = 870,
            distance = 1.9,
            deliveryTime = 29,
            deliveryFee = 25.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_013",
            name = "Wok This Way",
            description = "Chinese favourites, noodles, fried rice and spicy starters.",
            images = listOf(
                "https://images.unsplash.com/photo-1512058564366-18510be2db19"
            ),
            logo = "https://images.unsplash.com/photo-1563245372-f21724e3856d",
            cuisines = listOf("Chinese", "Asian", "Noodles"),
            rating = 4.4,
            totalRatings = 1250,
            distance = 1.4,
            deliveryTime = 24,
            deliveryFee = 20.0,
            minimumOrderAmount = 129.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_014",
            name = "Tandoori Tales",
            description = "Smoky tandoori dishes, kebabs and North Indian classics.",
            images = listOf(
                "https://images.unsplash.com/photo-1599487488170-d11ec9c172f0"
            ),
            logo = "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398",
            cuisines = listOf("Tandoori", "North Indian", "Kebab"),
            rating = 4.2,
            totalRatings = 690,
            distance = 2.7,
            deliveryTime = 32,
            deliveryFee = 30.0,
            minimumOrderAmount = 179.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_015",
            name = "Healthy Bowl",
            description = "Fresh salads, protein bowls and healthy meals.",
            images = listOf(
                "https://images.unsplash.com/photo-1512621776951-a57141f2eefd"
            ),
            logo = "https://images.unsplash.com/photo-1540420773420-3366772f4999",
            cuisines = listOf("Healthy", "Salads", "Continental"),
            rating = 4.7,
            totalRatings = 540,
            distance = 0.9,
            deliveryTime = 19,
            deliveryFee = 15.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = true
        ),

        Restaurant(
            id = "restaurant_016",
            name = "Chicken County",
            description = "Crispy chicken, burgers, wings and delicious combos.",
            images = listOf(
                "https://images.unsplash.com/photo-1562967914-608f82629710"
            ),
            logo = "https://images.unsplash.com/photo-1626082927389-6cd097cdc6ec",
            cuisines = listOf("Chicken", "Fast Food", "Burgers"),
            rating = 4.3,
            totalRatings = 1540,
            distance = 2.0,
            deliveryTime = 28,
            deliveryFee = 25.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_017",
            name = "Mishti Junction",
            description = "Traditional Bengali sweets, desserts and snacks.",
            images = listOf(
                "https://images.unsplash.com/photo-1605196560547-1c2b9f4a1a0d"
            ),
            logo = "https://images.unsplash.com/photo-1589119908995-c6837fa14848",
            cuisines = listOf("Desserts", "Bengali", "Sweets"),
            rating = 4.8,
            totalRatings = 2340,
            distance = 0.7,
            deliveryTime = 16,
            deliveryFee = 10.0,
            minimumOrderAmount = 79.0,
            isOpen = true,
            isPureVeg = true
        ),

        Restaurant(
            id = "restaurant_018",
            name = "The Pasta House",
            description = "Creamy pastas, pizzas and Italian comfort food.",
            images = listOf(
                "https://images.unsplash.com/photo-1473093295043-cdd812d0e601"
            ),
            logo = "https://images.unsplash.com/photo-1551183053-bf91a1d81141",
            cuisines = listOf("Italian", "Pasta", "Pizza"),
            rating = 4.4,
            totalRatings = 920,
            distance = 1.8,
            deliveryTime = 26,
            deliveryFee = 25.0,
            minimumOrderAmount = 179.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_019",
            name = "Street Food Hub",
            description = "Your favourite Indian street food under one roof.",
            images = listOf(
                "https://images.unsplash.com/photo-1601050690597-df0568f70950"
            ),
            logo = "https://images.unsplash.com/photo-1625398407796-82650a8c135f",
            cuisines = listOf("Street Food", "Indian", "Snacks"),
            rating = 4.2,
            totalRatings = 1870,
            distance = 1.1,
            deliveryTime = 22,
            deliveryFee = 15.0,
            minimumOrderAmount = 89.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_020",
            name = "Burger Lab",
            description = "Gourmet burgers with crispy fries and loaded sides.",
            images = listOf(
                "https://images.unsplash.com/photo-1568901346375-23c9450c58cd"
            ),
            logo = "https://images.unsplash.com/photo-1571091718767-18b5b1457add",
            cuisines = listOf("Burgers", "American", "Fast Food"),
            rating = 4.5,
            totalRatings = 1760,
            distance = 1.6,
            deliveryTime = 23,
            deliveryFee = 20.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_021",
            name = "Royal Thali",
            description = "Wholesome Indian thalis with a variety of authentic dishes.",
            images = listOf(
                "https://images.unsplash.com/photo-1601050690597-df0568f70950"
            ),
            logo = "https://images.unsplash.com/photo-1547592180-85f173990554",
            cuisines = listOf("Thali", "Indian", "North Indian"),
            rating = 4.6,
            totalRatings = 1430,
            distance = 2.2,
            deliveryTime = 31,
            deliveryFee = 25.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = true
        ),

        Restaurant(
            id = "restaurant_022",
            name = "Momo Nation",
            description = "Steamed, fried and spicy momos with delicious dips.",
            images = listOf(
                "https://images.unsplash.com/photo-1625220194771-7ebdea0b70b9"
            ),
            logo = "https://images.unsplash.com/photo-1563245372-f21724e3856d",
            cuisines = listOf("Momos", "Chinese", "Tibetan"),
            rating = 4.5,
            totalRatings = 1980,
            distance = 1.3,
            deliveryTime = 20,
            deliveryFee = 15.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_023",
            name = "Breakfast Club",
            description = "Fresh breakfast, sandwiches, omelettes and coffee.",
            images = listOf(
                "https://images.unsplash.com/photo-1533089860892-a7c6f0a88666"
            ),
            logo = "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb",
            cuisines = listOf("Breakfast", "Cafe", "Continental"),
            rating = 4.3,
            totalRatings = 720,
            distance = 0.6,
            deliveryTime = 17,
            deliveryFee = 10.0,
            minimumOrderAmount = 99.0,
            isOpen = true,
            isPureVeg = false
        ),

        Restaurant(
            id = "restaurant_024",
            name = "Biryani Express",
            description = "Aromatic dum biryani delivered hot and fresh.",
            images = listOf(
                "https://images.unsplash.com/photo-1563379091339-03246963d51a"
            ),
            logo = "https://images.unsplash.com/photo-1589302168068-964664d93dc0",
            cuisines = listOf("Biryani", "Mughlai", "Indian"),
            rating = 4.4,
            totalRatings = 2650,
            distance = 1.5,
            deliveryTime = 27,
            deliveryFee = 25.0,
            minimumOrderAmount = 149.0,
            isOpen = true,
            isPureVeg = false
        )
    )
}