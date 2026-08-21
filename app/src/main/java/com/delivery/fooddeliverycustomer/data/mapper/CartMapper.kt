package com.delivery.fooddeliverycustomer.data.mapper


import com.delivery.fooddeliverycustomer.data.local.entity.CartEntity
import com.delivery.fooddeliverycustomer.data.local.entity.CartItemEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.cart.CartDto
import com.delivery.fooddeliverycustomer.data.remote.dto.cart.CartItemDto
import com.delivery.fooddeliverycustomer.domain.model.cart.Cart
import com.delivery.fooddeliverycustomer.domain.model.cart.CartItem

fun CartDto.toEntity(): CartEntity {
    return CartEntity(
        id = id,
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
        syncStatus = "SYNCED",
        updatedAt = System.currentTimeMillis()
    )
}

fun CartEntity.toDomain(): Cart {
    return Cart(
        id = id,
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
        items = emptyList()
    )
}

fun Cart.toEntity(): CartEntity {
    return CartEntity(
        id = id,
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
        syncStatus = "PENDING",
        updatedAt = System.currentTimeMillis()
    )
}

fun Cart.toDto(): CartDto {
    return CartDto(
        id = id,
        userId = userId,
        restaurantId = restaurantId,
        restaurantName = restaurantName,
        restaurantImage = restaurantImage,
        items = items.map { it.toDto() },
        subtotal = subtotal,
        deliveryFee = deliveryFee,
        platformFee = platformFee,
        tax = tax,
        discount = discount,
        totalAmount = totalAmount,
        couponCode = coupon?.code,
        updatedAt = System.currentTimeMillis().toString()
    )
}

fun CartItem.toDto(): CartItemDto {
    return CartItemDto(
        foodItemId = foodItemId,
        name = name,
        image = image,
        basePrice = basePrice,
        finalPrice = finalPrice,
        quantity = quantity,
        totalPrice = totalPrice,
        customizations = emptyList()
    )
}

fun CartItemDto.toDomain(): CartItem {
    return CartItem(
        foodItemId = foodItemId,
        name = name,
        image = image,
        basePrice = basePrice,
        finalPrice = finalPrice,
        quantity = quantity,
        totalPrice = totalPrice,
        customizations = emptyList()
    )
}

fun CartItem.toEntity(cartId: String = ""): CartItemEntity {
    return CartItemEntity(
        cartId = cartId,
        foodItemId = foodItemId,
        name = name,
        image = image,
        basePrice = basePrice,
        finalPrice = finalPrice,
        quantity = quantity,
        totalPrice = totalPrice
    )
}

fun CartItemEntity.toDomain(): CartItem {
    return CartItem(
        foodItemId = foodItemId,
        name = name,
        image = image,
        basePrice = basePrice,
        finalPrice = finalPrice,
        quantity = quantity,
        totalPrice = totalPrice
    )
}
