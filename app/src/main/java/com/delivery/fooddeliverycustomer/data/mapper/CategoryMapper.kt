package com.delivery.fooddeliverycustomer.data.mapper

import com.delivery.fooddeliverycustomer.data.local.entity.FoodCategoryEntity
import com.delivery.fooddeliverycustomer.data.remote.dto.restaurant.FoodCategoryDto
import com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodCategory


fun FoodCategoryDto.toEntity(): FoodCategoryEntity {
    return FoodCategoryEntity(
        id = id,
        restaurantId = restaurantId,
        name = name,
        description = description ?: "",
        image = image,
        sortOrder = sortOrder,
        isActive = isActive
    )
}

fun FoodCategoryEntity.toDomain(): FoodCategory {
    return FoodCategory(
        id = id,
        restaurantId = restaurantId,
        name = name,
        description = description,
        image = image,
        sortOrder = sortOrder,
        isActive = isActive
    )
}

fun FoodCategory.toEntity(): FoodCategoryEntity {
    return FoodCategoryEntity(
        id = id,
        restaurantId = restaurantId,
        name = name,
        description = description,
        image = image,
        sortOrder = sortOrder,
        isActive = isActive
    )
}

fun FoodCategory.toDto(): FoodCategoryDto {
    return FoodCategoryDto(
        id = id,
        restaurantId = restaurantId,
        name = name,
        description = description,
        image = image,
        sortOrder = sortOrder,
        isActive = isActive
    )
}
