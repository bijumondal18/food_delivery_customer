package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "restaurants",
    indices = [
        Index(value = ["name"]),
        Index(value = ["isOpen"])
    ]
)
data class RestaurantEntity(

    @PrimaryKey
    val id: String,

    val name: String,

    val description: String,

    val logo: String?,

    val images: List<String>,

    val cuisines: List<String>,

    val rating: Double,

    val totalRatings: Int,

    val deliveryTime: Int,

    val deliveryFee: Double,

    val minimumOrderAmount: Double,

    val isOpen: Boolean,

    val isPureVeg: Boolean,

    val isPromoted: Boolean,

    val latitude: Double,

    val longitude: Double,

    val addressLine: String,

    val city: String,

    val state: String,

    val postalCode: String,

    val updatedAt: Long
)