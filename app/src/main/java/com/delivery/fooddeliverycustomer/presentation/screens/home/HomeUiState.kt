package com.delivery.fooddeliverycustomer.presentation.screens.home

import com.delivery.fooddeliverycustomer.domain.model.user.UserLocation

data class HomeUiState(
    val location: com.delivery.fooddeliverycustomer.domain.model.user.UserLocation? = null,
    val isLoadingLocation: Boolean = true,
    val isLoggedIn: Boolean = false,
    val profileImageUrl: String? = null
)

