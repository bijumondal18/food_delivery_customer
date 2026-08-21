package com.delivery.fooddeliverycustomer.data.mapper


import com.delivery.fooddeliverycustomer.data.remote.dto.order.OrderTimelineStepDto
import com.delivery.fooddeliverycustomer.domain.model.order.OrderTimelineStep

fun OrderTimelineStepDto.toDomain(): OrderTimelineStep {
    return OrderTimelineStep(
        status = status,
        title = title,
        description = description,
        timestamp = timestamp,
        completed = completed
    )
}