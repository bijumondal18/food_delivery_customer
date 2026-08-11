package com.delivery.fooddeliverycustomer.presentation.home

import com.delivery.fooddeliverycustomer.data.model.UserLocation

data class HomeUiState(
    val location: UserLocation? = null,
    val isLoadingLocation: Boolean = true,
    val isLoggedIn: Boolean = false,
    val profileImageUrl: String? = null
)

