package com.delivery.fooddeliverycustomer.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.R

data class SavedAddress(
    val id: String,
    val title: String,
    val address: String,
    val type: AddressType
)

enum class AddressType {
    HOME,
    WORK,
    OTHER
}

private val popularLocations = listOf(
    "Salt Lake",
    "New Town",
    "Park Street",
    "Sector V",
    "Howrah"
)

private val savedAddresses = listOf(
    SavedAddress(
        id = "1",
        title = "Home",
        address = "Salt Lake, Kolkata, West Bengal",
        type = AddressType.HOME
    ),
    SavedAddress(
        id = "2",
        title = "Office",
        address = "Sector V, Salt Lake, Kolkata",
        type = AddressType.WORK
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationBottomSheet(
    currentLocation: String?,
    onDismiss: () -> Unit,
    onUseCurrentLocation: () -> Unit,
    onAddNewAddress: () -> Unit,
    onLocationSelected: (String) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        dragHandle = {},
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
        shape = RoundedCornerShape(16.dp),
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = false
        ),
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {

            // Title
            item {

                Row {
                    Column(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Text(
                            text = "Select Location",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            text = "Choose where you want your order delivered",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(
                                top = 2.dp,
                                bottom = 4.dp
                            )
                        )
                    }

                    IconButton(
                        onClick = onDismiss
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05f)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.close_24px),
                                contentDescription = "Close Icon"
                            )
                        }

                }

            }


        }

        // Divider
        item {

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 12.dp
                )
            )
        }

        // Popular locations
        item {

            Text(
                text = "Popular locations",
                style = MaterialTheme.typography.titleMedium
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp,
                        bottom = 20.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(popularLocations) { location ->

                    FilterChip(
                        selected = false,
                        shape = CircleShape,
                        onClick = {
                            onLocationSelected(location)
                        },
                        label = {
                            Text(location)
                        }
                    )
                }
            }
        }

        // Current location
        item {

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onUseCurrentLocation
            ) {

                Icon(
                    painter = painterResource(R.drawable.my_location_24px),
                    contentDescription = null
                )

                Text(
                    text = "Use Current Location",
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            }
        }

        // Add address
        item {

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp
                    ),
                onClick = onAddNewAddress
            ) {

                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )

                Text(
                    text = "Add New Address",
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            }
        }

        // Divider
        item {

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 12.dp
                )
            )
        }

        // Saved addresses title
        item {

            Text(
                text = "Saved Addresses",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    bottom = 8.dp
                )
            )
        }

        // Saved addresses
        items(
            items = savedAddresses,
            key = { it.id }
        ) { address ->

            SavedAddressItem(
                address = address,
                onClick = {
                    onLocationSelected(address.address)
                }
            )
        }
    }
}
}