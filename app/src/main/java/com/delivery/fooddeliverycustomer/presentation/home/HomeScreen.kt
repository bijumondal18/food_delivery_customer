package com.delivery.fooddeliverycustomer.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.delivery.fooddeliverycustomer.presentation.auth.login.LoginBottomSheet
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    showLoginSheet: Boolean,
    onDismissLoginSheet: () -> Unit,
    onGoogleLogin: () -> Unit,
    onPhoneLogin: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLocation()
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Column {

                        Text(
                            text = "Deliver to"
                        )

                        Text(
                            text = state.location?.address
                                ?: "Fetching location...",
                            style =
                                androidx.compose.material3.MaterialTheme
                                    .typography
                                    .bodySmall
                        )
                    }
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            // TODO:
                            // Open location selector
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.LocationOn,
                            contentDescription =
                                "Location"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                        }
                    ) {

                        if (!state.profileImageUrl.isNullOrEmpty()) {

                            AsyncImage(
                                model = state.profileImageUrl,
                                contentDescription = "Profile",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )

                        } else {

                            Surface(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape),
                                color = MaterialTheme.colorScheme.primary
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile",
                                    tint = Color.White,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "What are you craving today?",
                style =
                    androidx.compose.material3.MaterialTheme
                        .typography
                        .headlineMedium
            )
        }
    }

    if (showLoginSheet) {

        LoginBottomSheet(

            onDismiss = {
                onDismissLoginSheet()
            },

            onGoogleLogin = {
                onGoogleLogin()
            },

            onPhoneLogin = {
                onPhoneLogin()
            }
        )
    }
}