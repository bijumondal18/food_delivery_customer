package com.delivery.fooddeliverycustomer.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "search_history",
    indices = [
        Index(value = ["userId"])
    ]
)
data class SearchHistoryEntity(

    @PrimaryKey
    val id: String,

    val userId: String,

    val query: String,

    val createdAt: Long
)