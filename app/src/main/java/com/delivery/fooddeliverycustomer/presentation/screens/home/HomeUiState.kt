package com.delivery.fooddeliverycustomer.presentation.screens.home

import com.delivery.fooddeliverycustomer.data.model.user.UserLocation

data class HomeUiState(
    val location: UserLocation? = null,
    val isLoadingLocation: Boolean = true,
    val isLoggedIn: Boolean = false,
    val profileImageUrl: String? = null
)

