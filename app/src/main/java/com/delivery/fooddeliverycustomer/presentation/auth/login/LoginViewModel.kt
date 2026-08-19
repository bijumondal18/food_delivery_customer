package com.delivery.fooddeliverycustomer.presentation.auth.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.fooddeliverycustomer.core.datastore.AppPreferences
import com.delivery.fooddeliverycustomer.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        LoginUiState()
    )

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun onEmailChanged(email: String) {

        _uiState.update {
            it.copy(
                email = email,
                error = null
            )
        }
    }

    fun onPasswordChanged(password: String) {

        _uiState.update {
            it.copy(
                password = password,
                error = null
            )
        }
    }

    fun login() {

        val state = _uiState.value

        if (state.email.isBlank()) {

            _uiState.update {
                it.copy(
                    error = "Please enter your email"
                )
            }

            return
        }

        if (state.password.isBlank()) {

            _uiState.update {
                it.copy(
                    error = "Please enter your password"
                )
            }

            return
        }

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            val result = authRepository.loginWithEmail(
                email = state.email.trim(),
                password = state.password
            )

            result
                .onSuccess { user ->
                    appPreferences.saveUser(
                        userId = user.uid,
                        email = user.email,
                        name = user.displayName
                    )

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoginSuccessful = true
                        )
                    }
                }
                .onFailure { exception ->

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message
                                ?: "Login failed"
                        )
                    }
                }
        }
    }

    fun clearLoginSuccess() {

        _uiState.update {
            it.copy(
                isLoginSuccessful = false
            )
        }
    }
}