package com.delivery.fooddeliverycustomer.domain.model.restaurant

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek

data class RestaurantTiming (
    val day: DayOfWeek = DayOfWeek.MONDAY,

    val isOpen: Boolean = true,

    val openingTime: String = "09:00",
    val closingTime: String = "23:00"
)