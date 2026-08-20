package com.delivery.fooddeliverycustomer.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.fooddeliverycustomer.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class AuthDestination {
    data object Login : AuthDestination()
    data object Home : AuthDestination()
}


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _destination =
        MutableStateFlow<AuthDestination?>(null)

    val destination = _destination.asStateFlow()

    init {
        checkAuthentication()
    }

    private fun checkAuthentication() {

        viewModelScope.launch {

            delay(1200)

            if (authRepository.isLoggedIn()) {
                _destination.value = AuthDestination.Home
            } else {
                _destination.value = AuthDestination.Login
            }
        }
    }
}