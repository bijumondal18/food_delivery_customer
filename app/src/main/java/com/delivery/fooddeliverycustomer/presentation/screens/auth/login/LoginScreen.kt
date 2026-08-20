package com.delivery.fooddeliverycustomer.presentation.screens.auth.login

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.components.EmailField
import com.delivery.fooddeliverycustomer.core.components.PasswordField
import com.delivery.fooddeliverycustomer.presentation.components.buttons.PrimaryButton
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onGoogleLogin: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val isKeyboardVisible = WindowInsets.isImeVisible
    /*
     * Navigate to Home after successful login.
     */
    LaunchedEffect(state.isLoginSuccessful) {

        if (state.isLoginSuccessful) {

            onLoginSuccess()

            viewModel.clearLoginSuccess()
        }
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            3
        }
    )

    /*
     * Auto slide images.
     */
    LaunchedEffect(Unit) {

        while (true) {

            delay(3500.milliseconds)

            val nextPage =
                (pagerState.currentPage + 1) % pagerState.pageCount

            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = tween(
                    durationMillis = 900,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {

        // =====================================================
        // IMAGE SLIDER - 60%
        // =====================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(
                    if (isKeyboardVisible) 0.2f else 0.60f
                )
        ) {

            val images = remember {
                listOf(
                    R.drawable.food_image_1,
                    R.drawable.food_image_2,
                    R.drawable.food_image_3
                )
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->

                AsyncImage(
                    model = images[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Dark gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Color.Black.copy(alpha = 0.25f)
                    )
            )

            // Page indicators
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                repeat(
                    pagerState.pageCount
                ) { index ->

                    Box(
                        modifier = Modifier
                            .height(6.dp)
                            .width(
                                if (
                                    pagerState.currentPage == index
                                ) {
                                    22.dp
                                } else {
                                    6.dp
                                }
                            )
                            .clip(CircleShape)
                            .background(
                                if (
                                    pagerState.currentPage == index
                                ) {
                                    Color.White
                                } else {
                                    Color.White.copy(alpha = 0.5f)
                                }
                            )
                    )
                }
            }
        }

        // =====================================================
        // LOGIN AREA - 40%
        // =====================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.40f)
                .clip(
                    RoundedCornerShape(
                        topStart = 28.dp,
                        topEnd = 28.dp
                    )
                )
                .background(
                    MaterialTheme.colorScheme.surface
                )
                .verticalScroll(
                    rememberScrollState()
                )
                .imePadding()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login or Sign up",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )


            EmailField(
                value = state.email,
                onValueChange = viewModel::onEmailChanged,
                enabled = !state.isLoading
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            PasswordField(
                value = state.password,
                onValueChange = viewModel::onPasswordChanged,
                enabled = !state.isLoading
            )

            // =================================================
            // ERROR
            // =================================================

            state.error?.let { error ->

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = error,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            PrimaryButton(
                text = "Login",
                onClick = viewModel::login,
                isLoading = state.isLoading
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =================================================
            // OR
            // =================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            MaterialTheme.colorScheme.outlineVariant
                        )
                )

                Text(
                    text = "  OR  ",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            MaterialTheme.colorScheme.outlineVariant
                        )
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =================================================
            // GOOGLE LOGIN
            // =================================================

            Button(
                onClick = onGoogleLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {

                Text(
                    text = "G",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = "Continue with Google",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}