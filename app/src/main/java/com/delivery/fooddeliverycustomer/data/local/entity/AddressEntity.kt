package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "addresses",
    indices = [
        Index(value = ["userId"])
    ]
)
data class AddressEntity(

    @PrimaryKey
    val id: String,

    val userId: String,

    val label: String,

    val name: String,

    val phone: String,

    val addressLine1: String,

    val addressLine2: String,

    val landmark: String,

    val city: String,

    val state: String,

    val country: String,

    val postalCode: String,

    val latitude: Double,

    val longitude: Double,

    val isDefault: Boolean
)