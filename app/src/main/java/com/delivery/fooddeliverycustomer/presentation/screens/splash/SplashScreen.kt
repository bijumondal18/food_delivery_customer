package com.delivery.fooddeliverycustomer.presentation.screens.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.delivery.fooddeliverycustomer.core.theme.Quicksand
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {

    val context = LocalContext.current

    val alpha = remember {
        Animatable(0f)
    }

    val scale = remember {
        Animatable(0.65f)
    }

    var splashFinished by remember {
        mutableStateOf(false)
    }

    var locationPermissionCompleted by remember {
        mutableStateOf(false)
    }

    var notificationPermissionCompleted by remember {
        mutableStateOf(false)
    }

    // --------------------------------------------------
    // Location Permission
    // --------------------------------------------------

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) {
            // Whether allowed or denied,
            // continue to notification permission.
            locationPermissionCompleted = true
        }

    // --------------------------------------------------
    // Notification Permission
    // --------------------------------------------------

    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            // Whether allowed or denied,
            // continue to Home.
            notificationPermissionCompleted = true
        }

    // --------------------------------------------------
    // Splash Animation
    // --------------------------------------------------

    LaunchedEffect(Unit) {

        coroutineScope {

            launch {
                alpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 450,
                        easing = FastOutSlowInEasing
                    )
                )
            }

            launch {
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 450,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }

        delay(150)

        splashFinished = true
    }

    // --------------------------------------------------
    // STEP 1
    // Check Location Permission
    // --------------------------------------------------

    LaunchedEffect(splashFinished) {

        if (!splashFinished) {
            return@LaunchedEffect
        }

        val fineGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val coarseGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (fineGranted || coarseGranted) {

            // Already granted
            locationPermissionCompleted = true

        } else {

            // Ask location permission
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // --------------------------------------------------
    // STEP 2
    // Notification Permission
    // --------------------------------------------------

    LaunchedEffect(locationPermissionCompleted) {

        if (!locationPermissionCompleted) {
            return@LaunchedEffect
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            val notificationGranted =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

            if (notificationGranted) {

                notificationPermissionCompleted = true

            } else {

                notificationPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }

        } else {

            notificationPermissionCompleted = true
        }
    }

    // --------------------------------------------------
    // STEP 3
    // Navigate Home
    // --------------------------------------------------

    LaunchedEffect(notificationPermissionCompleted) {

        if (!notificationPermissionCompleted) {
            return@LaunchedEffect
        }

        val currentUser = FirebaseAuth
            .getInstance()
            .currentUser

        if (currentUser != null) {

            onNavigateToHome()

        } else {

            onNavigateToLogin()
        }
    }

    // --------------------------------------------------
    // UI
    // --------------------------------------------------

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primary
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "Khabo",
            modifier = Modifier
                .alpha(alpha.value)
                .scale(scale.value),
            color = Color.White,
            fontSize = 52.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Quicksand
        )
    }
}