package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.local.entity.FoodItemEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.restaurant.FoodItemDto
import com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodItem

fun FoodItemDto.toEntity(): FoodItemEntity {
    return FoodItemEntity(
        id = id,
        restaurantId = restaurantId,
        categoryId = categoryId,
        name = name,
        description = description ?: "",
        image = image,
        price = price,
        discountedPrice = discountedPrice,
        isVeg = isVeg,
        isEgg = isEgg,
        isAvailable = isAvailable,
        isBestSeller = isBestSeller,
        rating = rating,
        totalRatings = totalRatings,
        tags = tags,
        preparationTime = preparationTime
    )
}

fun FoodItemEntity.toDomain(): FoodItem {
    return FoodItem(
        id = id,
        restaurantId = restaurantId,
        categoryId = categoryId,
        name = name,
        description = description,
        image = image,
        price = price,
        discountedPrice = discountedPrice,
        isVeg = isVeg,
        isEgg = isEgg,
        isAvailable = isAvailable,
        isBestSeller = isBestSeller,
        rating = rating,
        totalRatings = totalRatings,
        tags = tags,
        preparationTime = preparationTime
    )
}

fun FoodItem.toEntity(): FoodItemEntity {
    return FoodItemEntity(
        id = id,
        restaurantId = restaurantId,
        categoryId = categoryId,
        name = name,
        description = description,
        image = image,
        price = price,
        discountedPrice = discountedPrice,
        isVeg = isVeg,
        isEgg = isEgg,
        isAvailable = isAvailable,
        isBestSeller = isBestSeller,
        rating = rating,
        totalRatings = totalRatings,
        tags = tags,
        preparationTime = preparationTime
    )
}

fun FoodItem.toDto(): FoodItemDto {
    return FoodItemDto(
        id = id,
        restaurantId = restaurantId,
        categoryId = categoryId,
        name = name,
        description = description,
        image = image,
        price = price,
        discountedPrice = discountedPrice,
        isVeg = isVeg,
        isEgg = isEgg,
        isAvailable = isAvailable,
        isBestSeller = isBestSeller,
        rating = rating,
        totalRatings = totalRatings,
        tags = tags,
        preparationTime = preparationTime,
        customizationGroups = emptyList()
    )
}
