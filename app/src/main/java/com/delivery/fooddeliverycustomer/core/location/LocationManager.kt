package com.delivery.fooddeliverycustomer.core.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class LocationManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location? {

        return suspendCancellableCoroutine { continuation ->

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    continuation.resume(location)
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }

    suspend fun getAddress(
        latitude: Double,
        longitude: Double
    ): String {

        return withContext(Dispatchers.IO) {

            try {

                val geocoder = Geocoder(
                    context
                )

                @Suppress("DEPRECATION")
                val addresses: List<Address> =
                    geocoder.getFromLocation(
                        latitude,
                        longitude,
                        1
                    ) ?: emptyList()

                if (addresses.isNotEmpty()) {
                    val address = addresses[0]
                    buildList {
                        address.subLocality?.takeIf { it.isNotBlank() }?.let { add(it) }
                        address.locality?.takeIf { it.isNotBlank() }?.let { add(it) }
                    }
                        .distinct()
                        .joinToString(", ")
                        .ifEmpty {
                            "Current location"
                        }

                } else {

                    "Current location"
                }

            } catch (e: Exception) {

                "Current location"
            }
        }
    }
}