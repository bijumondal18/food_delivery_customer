package com.delivery.fooddeliverycustomer.data.mapper


import com.delivery.fooddeliverycustomer.data.remote.dto.order.DeliveryPartnerDto
import com.delivery.fooddeliverycustomer.domain.model.delivery.DeliveryPartner

fun DeliveryPartnerDto.toDomain(): DeliveryPartner {
    return DeliveryPartner(
        id = id,
        name = name,
        phone = phone?:"",
        profileImage = profileImage,
        rating = rating?:0.0,
        vehicleNumber = vehicleNumber?:""
    )
}