package com.delivery.fooddeliverycustomer.presentation.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.delivery.fooddeliverycustomer.core.location.LocationManager
import com.delivery.fooddeliverycustomer.core.ui.theme.AppDarkGradient
import com.delivery.fooddeliverycustomer.core.ui.theme.AppLightGradient
import com.delivery.fooddeliverycustomer.core.ui.theme.Quicksand
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit
) {

    val context = LocalContext.current

    val locationManager = remember {
        LocationManager(context)
    }

    val alpha = remember {
        Animatable(0f)
    }

    val scale = remember {
        Animatable(0.85f)
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
    // Notification Permission Launcher
    // --------------------------------------------------

    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            // Whether accepted OR denied, continue.
            notificationPermissionCompleted = true
        }

    // --------------------------------------------------
    // Location Permission Launcher
    // --------------------------------------------------

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) {
            // Whether accepted OR denied, continue.
            locationPermissionCompleted = true
        }

    // --------------------------------------------------
    // Splash Animation
    // --------------------------------------------------

    LaunchedEffect(Unit) {

        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )

        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )

        delay(500)

        splashFinished = true
    }

    // --------------------------------------------------
    // STEP 1: Location Permission
    // --------------------------------------------------

    LaunchedEffect(splashFinished) {

        if (!splashFinished) return@LaunchedEffect

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

            // Already granted.
            // Do NOT ask again.
            locationPermissionCompleted = true

        } else {

            // Ask only once.
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    // --------------------------------------------------
    // STEP 2: Fetch Location + Notification Permission
    // --------------------------------------------------

    LaunchedEffect(locationPermissionCompleted) {

        if (!locationPermissionCompleted) {
            return@LaunchedEffect
        }

        // ----------------------------------------------
        // Fetch current location
        // ----------------------------------------------

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

            val location =
                locationManager.getCurrentLocation()

            if (location != null) {

                val latitude = location.latitude
                val longitude = location.longitude

                println("TastyGo Location")
                println("Latitude: $latitude")
                println("Longitude: $longitude")

                // TODO:
                // Save location in DataStore/ViewModel
            }
        }

        // ----------------------------------------------
        // Notification Permission
        // ----------------------------------------------

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            val notificationGranted =
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

            if (notificationGranted) {

                // Already granted.
                // Do NOT ask again.
                notificationPermissionCompleted = true

            } else {

                notificationPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }

        } else {

            // Android 12 and below.
            notificationPermissionCompleted = true
        }
    }

    // --------------------------------------------------
    // STEP 3: Navigate to Login
    // --------------------------------------------------

    LaunchedEffect(notificationPermissionCompleted) {

        if (!notificationPermissionCompleted) {
            return@LaunchedEffect
        }

        delay(300)

        onNavigateToMain()
    }

    // --------------------------------------------------
    // UI
    // --------------------------------------------------

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = if (isSystemInDarkTheme()) {
                    AppDarkGradient
                } else {
                    AppLightGradient
                }
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "TastyGo",
            modifier = Modifier
                .alpha(alpha.value)
                .scale(scale.value),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 42.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Quicksand
        )
    }
}


private fun requestNotificationPermission(
    context: android.content.Context,
    launcher: androidx.activity.result.ActivityResultLauncher<String>
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

        val granted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

        if (granted) {
            return
        }

        launcher.launch(
            Manifest.permission.POST_NOTIFICATIONS
        )

    } else {
        // Android 12 and below don't require runtime notification permission.
    }
}