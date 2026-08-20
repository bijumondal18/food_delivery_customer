package com.delivery.fooddeliverycustomer.presentation.screens.splash

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.fooddeliverycustomer.core.datastore.AppPreferences
import com.delivery.fooddeliverycustomer.core.location.LocationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartupViewModel @Inject constructor(
    private val locationManager: LocationManager,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        StartupUiState()
    )

    val uiState = _uiState.asStateFlow()

    fun setLocation(location: Location?) {
        _uiState.value = _uiState.value.copy(
            location = location,
            locationCompleted = true
        )
    }

    fun setLocationCompleted() {
        _uiState.value = _uiState.value.copy(
            locationCompleted = true
        )
    }

    fun setNotificationCompleted() {
        _uiState.value = _uiState.value.copy(
            notificationCompleted = true
        )
    }


    fun fetchLocation() {

        viewModelScope.launch {

            val location =
                locationManager.getCurrentLocation()

            _uiState.value =
                _uiState.value.copy(
                    location = location,
                    locationCompleted = true
                )
        }
    }

    fun markNotificationCompleted() {

        _uiState.value =
            _uiState.value.copy(
                notificationCompleted = true
            )
    }

}