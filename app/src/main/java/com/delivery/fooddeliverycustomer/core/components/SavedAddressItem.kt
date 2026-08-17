package com.delivery.fooddeliverycustomer.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.R

@Composable
fun SavedAddressItem(
    address: SavedAddress,
    onClick: () -> Unit
) {

    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        headlineContent = {
            Text(
                text = address.title,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingContent = {
            Text(
                text = address.address,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        leadingContent = {

            Icon(
                painter = when (address.type) {
                    AddressType.HOME -> painterResource(R.drawable.home_pin_24px)
                    AddressType.WORK -> painterResource(R.drawable.work_24px)
                    AddressType.OTHER -> painterResource(R.drawable.location_on_24px)
                },
                contentDescription = address.title
            )
        }
    )
}