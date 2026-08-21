package com.delivery.fooddeliverycustomer.data.mapper


import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderItemDto
import com.delivery.fooddeliverycustomer.domain.model.order.OrderItem
import kotlin.collections.map

fun OrderItemDto.toDomain(): OrderItem {
    return OrderItem(
        id = id,
        foodItemId = foodItemId,

        name = name,
        image = image,

        price = price,
        quantity = quantity,

//        customizations = customizations.map {
//            it.toDomain()
//        },

        totalPrice = totalPrice
    )
}