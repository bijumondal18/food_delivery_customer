package com.delivery.fooddeliverycustomer.data.model

data class Restaurant(
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: String,
    val distance: String = "1.2 km",
    val offer: String = "50% OFF",
    val deliveryTime: String = "30–35 min",
    val isFavourite: Boolean = false
)

val restaurants = listOf(

    Restaurant(
        name = "Food Palace",
        description = "Indian, Biryani",
        rating = "4.5",
        imageUrl = "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4"
    ),

    Restaurant(
        name = "Pizza Hub",
        description = "Pizza, Italian",
        rating = "4.4",
        imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591"
    ),

    Restaurant(
        name = "Burger House",
        description = "Burgers, Fast Food",
        rating = "4.3",
        imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd"
    ),

    Restaurant(
        name = "Spice Garden",
        description = "Indian, North Indian",
        rating = "4.6",
        imageUrl = "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f"
    ),

    Restaurant(
        name = "Dragon Wok",
        description = "Chinese, Asian",
        rating = "4.2",
        imageUrl = "https://images.unsplash.com/photo-1512058564366-18510be2db19"
    ),

    Restaurant(
        name = "Sweet Treats",
        description = "Desserts, Bakery",
        rating = "4.7",
        imageUrl = "https://images.unsplash.com/photo-1551024506-0bccd828d307"
    )
)