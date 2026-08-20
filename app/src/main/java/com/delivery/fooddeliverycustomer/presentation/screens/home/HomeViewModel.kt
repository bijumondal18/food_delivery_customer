package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.fooddeliverycustomer.core.location.LocationManager
import com.delivery.fooddeliverycustomer.data.model.user.UserLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val locationManager: LocationManager
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(HomeUiState())

    val uiState = _uiState.asStateFlow()

    fun loadLocation() {

        viewModelScope.launch {

            val location = locationManager.getCurrentLocation()

            if (location != null) {

                val address = locationManager.getAddress(
                    location.latitude,
                    location.longitude
                )

                _uiState.update {
                    it.copy(
                        location = UserLocation(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            address = address
                        )
                    )
                }
            }
        }
    }
}