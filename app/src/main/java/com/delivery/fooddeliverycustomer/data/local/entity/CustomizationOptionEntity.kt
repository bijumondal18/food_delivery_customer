package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customization_options",
    indices = [
        Index(value = ["groupId"])
    ]
)
data class CustomizationOptionEntity(

    @PrimaryKey
    val id: String,

    val groupId: String,

    val name: String,

    val price: Double,

    val isAvailable: Boolean
)