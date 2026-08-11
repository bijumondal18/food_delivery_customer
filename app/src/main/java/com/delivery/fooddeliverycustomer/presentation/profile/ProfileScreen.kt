package com.delivery.fooddeliverycustomer.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.ui.theme.AppDarkGradient
import com.delivery.fooddeliverycustomer.core.ui.theme.AppHomeGradient
import com.delivery.fooddeliverycustomer.core.ui.theme.AppLightGradient

@Composable
fun ProfileScreen(
    profileImageUrl: String? = null,
    name: String = "Biju Mondal",
    phone: String = "+91 98765 43210",
    onEditProfile: () -> Unit = {},
    onLogout: () -> Unit = {}
) {

    Scaffold { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {

            // Header
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = if (isSystemInDarkTheme()) {
                                AppDarkGradient
                            } else {
                                AppLightGradient
                            }
                        )
                        .padding(
                            horizontal = 20.dp,
                            vertical = 28.dp
                        )
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Box {

                            if (!profileImageUrl.isNullOrEmpty()) {

                                AsyncImage(
                                    model = profileImageUrl,
                                    contentDescription = "Profile",
                                    modifier = Modifier
                                        .size(92.dp)
                                        .clip(CircleShape)
                                )

                            } else {

                                Box(
                                    modifier = Modifier
                                        .size(92.dp)
                                        .clip(CircleShape)
                                        .background(
                                            MaterialTheme.colorScheme.primary
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            }

                            IconButton(
                                onClick = onEditProfile,
                                modifier = Modifier
                                    .size(34.dp)
                                    .align(Alignment.BottomEnd)
                                    .clip(CircleShape)
                                    .background(
                                        MaterialTheme.colorScheme.onBackground
                                    )
                                    .padding(2.dp)

                            ) {

                                Icon(
                                    painter = painterResource(R.drawable.edit_24px),
                                    contentDescription = "Edit profile",
                                    tint = MaterialTheme.colorScheme.background,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = phone,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            item {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = "Account Settings",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    ProfileMenuItem(
                        icon = R.drawable.location_on_24px,
                        title = "Saved Addresses",
                        subtitle = "Manage your delivery addresses",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.notifications_24px,
                        title = "Notifications",
                        subtitle = "Manage notification preferences",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.settings_24px,
                        title = "Settings",
                        subtitle = "App preferences",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.help_24px,
                        title = "Help & Support",
                        subtitle = "We're here to help",
                        onClick = {}
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "Feedback",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    ProfileMenuItem(
                        icon = R.drawable.help_24px,
                        title = "Terms & Conditions",
                        subtitle = "",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.help_24px,
                        title = "Privacy Policy",
                        subtitle = "",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.help_24px,
                        title = "Restaurant Partner Terms & Conditions",
                        subtitle = "",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.help_24px,
                        title = "TastyGo Refund Policy",
                        subtitle = "",
                        onClick = {}
                    )

                    ProfileMenuItem(
                        icon = R.drawable.help_24px,
                        title = "Customer Support",
                        subtitle = "",
                        onClick = {}
                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    ProfileMenuItem(
                        icon = R.drawable.logout_24px,
                        title = "Logout",
                        subtitle = "Sign out from your account",
                        iconTint = MaterialTheme.colorScheme.primary,
                        onClick = onLogout
                    )
                }
            }

        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: Int,
    title: String,
    subtitle: String,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            focusedElevation = 0.dp,
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.08f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = iconTint
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                painter = painterResource(R.drawable.chevron_right_24px),
                contentDescription = null
            )
        }
    }
}