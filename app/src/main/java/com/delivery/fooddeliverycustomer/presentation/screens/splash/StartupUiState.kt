package com.delivery.fooddeliverycustomer.presentation.screens.splash

import android.location.Location

data class StartupUiState(
    val location: Location? = null,
    val locationCompleted: Boolean = false,
    val notificationCompleted: Boolean = false
) {
    val startupCompleted: Boolean
        get() = locationCompleted && notificationCompleted
}