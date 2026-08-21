package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.local.entity.OrderEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderDto
import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderItemDto
import com.delivery.fooddeliverycustomer.domain.model.location.Address
import com.delivery.fooddeliverycustomer.domain.model.order.Order
import com.delivery.fooddeliverycustomer.domain.model.order.OrderItem
import com.delivery.fooddeliverycustomer.domain.model.order.OrderStatus
import com.delivery.fooddeliverycustomer.domain.model.payment.PaymentMethod
import com.delivery.fooddeliverycustomer.domain.model.payment.PaymentStatus

fun OrderDto.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        orderNumber = orderNumber,
        userId = userId,
        restaurantId = restaurantId,
        restaurantName = restaurantName,
        restaurantImage = restaurantImage,
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
        estimatedDeliveryTime = estimatedDeliveryTime?.toLongOrNull(),
        createdAt = createdAt.toLongOrNull() ?: 0L,
        updatedAt = updatedAt.toLongOrNull() ?: 0L
    )
}

fun OrderEntity.toDomain(): Order {
    return Order(
        id = id,
        orderNumber = orderNumber,
        userId = userId,
        restaurantId = restaurantId,
        restaurantName = restaurantName,
        restaurantImage = restaurantImage,
        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,
        totalAmount = totalAmount,
        paymentMethod = try {
            PaymentMethod.valueOf(paymentMethod)
        } catch (e: Exception) {
            PaymentMethod.CASH_ON_DELIVERY
        },
        paymentStatus = try {
            PaymentStatus.valueOf(paymentStatus)
        } catch (e: Exception) {
            PaymentStatus.PENDING
        },
        orderStatus = try {
            OrderStatus.valueOf(orderStatus)
        } catch (e: Exception) {
            OrderStatus.PLACED
        },
        couponCode = couponCode,
        estimatedDeliveryTime = estimatedDeliveryTime,
        createdAt = createdAt,
        updatedAt = updatedAt,
        items = emptyList(), // Items are stored in a separate table
        deliveryAddress = null // Address is not stored in OrderEntity
    )
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        orderNumber = orderNumber,
        userId = userId,
        restaurantId = restaurantId,
        restaurantName = restaurantName,
        restaurantImage = restaurantImage,
        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,
        totalAmount = totalAmount,
        paymentMethod = paymentMethod.name,
        paymentStatus = paymentStatus.name,
        orderStatus = orderStatus.name,
        couponCode = couponCode,
        estimatedDeliveryTime = estimatedDeliveryTime,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Order.toDto(): OrderDto {
    return OrderDto(
        id = id,
        orderNumber = orderNumber,
        userId = userId,
        restaurantId = restaurantId,
        restaurantName = restaurantName,
        restaurantImage = restaurantImage,
        items = items.map { it.toDto() },
        deliveryAddress = deliveryAddress?.toDto() ?: throw IllegalArgumentException("Delivery address is required"),
        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,
        totalAmount = totalAmount,
        paymentMethod = paymentMethod.name,
        paymentStatus = paymentStatus.name,
        orderStatus = orderStatus.name,
        couponCode = couponCode,
        estimatedDeliveryTime = estimatedDeliveryTime?.toString(),
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString()
    )
}

fun OrderDto.toDomain(): Order {
    return Order(
        id = id,
        orderNumber = orderNumber,
        userId = userId,
        restaurantId = restaurantId,
        restaurantName = restaurantName,
        restaurantImage = restaurantImage,
        items = items.map { it.toDomain() },
        deliveryAddress = deliveryAddress.toDomain(),
        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,
        totalAmount = totalAmount,
        paymentMethod = try {
            PaymentMethod.valueOf(paymentMethod)
        } catch (e: Exception) {
            PaymentMethod.CASH_ON_DELIVERY
        },
        paymentStatus = try {
            PaymentStatus.valueOf(paymentStatus)
        } catch (e: Exception) {
            PaymentStatus.PENDING
        },
        orderStatus = try {
            OrderStatus.valueOf(orderStatus)
        } catch (e: Exception) {
            OrderStatus.PLACED
        },
        couponCode = couponCode,
        estimatedDeliveryTime = estimatedDeliveryTime?.toLongOrNull(),
        createdAt = createdAt.toLongOrNull() ?: 0L,
        updatedAt = updatedAt.toLongOrNull() ?: 0L
    )
}

fun OrderItemDto.toDomain(): OrderItem {
    return OrderItem(
        foodItemId = foodItemId,
        name = name,
        image = image,
        price = price,
        quantity = quantity,
        totalPrice = totalPrice
    )
}

fun OrderItem.toDto(): OrderItemDto {
    return OrderItemDto(
        foodItemId = foodItemId,
        name = name,
        image = image,
        price = price,
        quantity = quantity,
        totalPrice = totalPrice,
        customizations = emptyList()
    )
}
