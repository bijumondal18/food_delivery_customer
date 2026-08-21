package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customization_groups",
    indices = [
        Index(value = ["foodItemId"])
    ]
)
data class CustomizationGroupEntity(

    @PrimaryKey
    val id: String,

    val foodItemId: String,

    val name: String,

    val type: String,

    val isRequired: Boolean,

    val minSelection: Int,

    val maxSelection: Int
)