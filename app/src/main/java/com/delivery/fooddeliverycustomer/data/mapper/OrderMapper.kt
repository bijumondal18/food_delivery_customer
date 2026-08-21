package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.local.entity.OrderEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.address.AddressDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.CreateOrderRequestDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderDto
import com.delivery.fooddeliverycustomer.domain.model.order.CreateOrderRequest
import com.delivery.fooddeliverycustomer.domain.model.order.Order
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val gson = Gson()

fun OrderDto.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        orderNumber = orderNumber,

        userId = userId,
        restaurantId = restaurantId,

        restaurantName = restaurantName,
        restaurantImage = restaurantImage,

        itemsJson = gson.toJson(items),

        deliveryAddressJson = deliveryAddress?.let {
            gson.toJson(it)
        },

        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,

        totalAmount = totalAmount,

        paymentMethod = paymentMethod,
        paymentStatus = paymentStatus,

        orderStatus = orderStatus,

        couponCode = couponCode,

        estimatedDeliveryTime = estimatedDeliveryTime,

        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun OrderEntity.toDomain(): Order {

    val itemsType =
        object : TypeToken<List<com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderItemDto>>() {}.type

    val itemDtos: List<com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderItemDto> =
        gson.fromJson(itemsJson, itemsType)

    return Order(
        id = id,
        orderNumber = orderNumber,

        userId = userId,
        restaurantId = restaurantId,

        restaurantName = restaurantName,
        restaurantImage = restaurantImage,

        items = itemDtos.map {
            it.toDomain()
        },

        deliveryAddress = deliveryAddressJson?.let {
            gson.fromJson(
                it,
                AddressDto::class.java
            ).toDomain()
        },

        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,

        totalAmount = totalAmount,

        paymentMethod = runCatching {
            com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod.valueOf(
                paymentMethod
            )
        }.getOrDefault(
            com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod.CASH_ON_DELIVERY
        ),

        paymentStatus = runCatching {
            com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus.valueOf(
                paymentStatus
            )
        }.getOrDefault(
            com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus.PENDING
        ),

        orderStatus = runCatching {
            com.delivery.fooddeliverycustomer.domain.model.order.OrderStatus.valueOf(
                orderStatus
            )
        }.getOrDefault(
            com.delivery.fooddeliverycustomer.domain.model.order.OrderStatus.PLACED
        ),

        couponCode = couponCode,

        estimatedDeliveryTime = estimatedDeliveryTime,

        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun CreateOrderRequest.toDto(): CreateOrderRequestDto {
    return CreateOrderRequestDto(
        restaurantId = restaurantId,
        addressId = addressId,
        paymentMethod = paymentMethod,
        couponCode = couponCode,
        notes = notes
    )
}