package com.delivery.fooddeliverycustomer.data.mapper


import com.delivery.fooddeliverycustomer.data.remote.dto.auth.UserDto
import com.delivery.fooddeliverycustomer.domain.model.user.User

fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email.orEmpty(),
        phone = phone.orEmpty(),
        profileImage = profileImage
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        id = id,
        name = name,
        email = email,
        phone = phone,
        profileImage = profileImage,
        isEmailVerified = false,
        isPhoneVerified = false,
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString()
    )
}
