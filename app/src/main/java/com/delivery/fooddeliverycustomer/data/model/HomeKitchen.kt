package com.delivery.fooddeliverycustomer.data.model

data class HomeKitchen(
    val id: String,
    val name: String,
    val imageUrl: String,
    val distance: String,
    val deliveryTime: String
)


val homeKitchens = listOf(
    HomeKitchen(
        id = "1",
        name = "Maa's Kitchen",
        imageUrl = "https://images.unsplash.com/photo-1556911220-bff31c812dba",
        distance = "1.2 km",
        deliveryTime = "25-30 min"
    ),
    HomeKitchen(
        id = "2",
        name = "Bengali Home Food",
        imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950",
        distance = "1.8 km",
        deliveryTime = "30-35 min"
    ),
    HomeKitchen(
        id = "3",
        name = "Ghar Ka Khana",
        imageUrl = "https://images.unsplash.com/photo-1547592180-85f173990554",
        distance = "2.1 km",
        deliveryTime = "25-30 min"
    ),
    HomeKitchen(
        id = "4",
        name = "Annapurna Kitchen",
        imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4",
        distance = "2.5 km",
        deliveryTime = "35-40 min"
    )
)