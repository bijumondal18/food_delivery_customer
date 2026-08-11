package com.delivery.fooddeliverycustomer.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private data class ProfileMenu(val icon: Int, val title: String, val subtitle: String = "")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileImageUrl: String? = null,
    name: String = "Biju Mondal",
    phone: String = "+91 98765 43210",
    onEditProfile: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    val listState = rememberLazyListState()
    val isDarkTheme = androidx.compose.foundation.isSystemInDarkTheme()
    var contentVisible by remember { mutableStateOf(false) }
    /* * Trigger the screen entrance animation only once. */

    LaunchedEffect(Unit) {
        contentVisible = true
    }

    /* * Static menu data is remembered so that these lists * aren't recreated on every recomposition. */
    val accountItems = remember {
        listOf(
            ProfileMenu(
                icon = R.drawable.location_on_24px,
                title = "Saved Addresses",
                subtitle = "Manage your delivery addresses"
            ),
            ProfileMenu(
                icon = R.drawable.notifications_24px,
                title = "Notifications",
                subtitle = "Manage notification preferences"
            ),
            ProfileMenu(
                icon = R.drawable.settings_24px,
                title = "Settings",
                subtitle = "App preferences"
            ),
            ProfileMenu(
                icon = R.drawable.help_24px,
                title = "Help & Support",
                subtitle = "We're here to help"
            )
        )
    }
    val feedbackItems = remember {
        listOf(
            ProfileMenu(icon = R.drawable.help_24px, title = "Terms & Conditions"),
            ProfileMenu(icon = R.drawable.help_24px, title = "Privacy Policy"),
            ProfileMenu(
                icon = R.drawable.help_24px,
                title = "Restaurant Partner Terms & Conditions"
            ),
            ProfileMenu(icon = R.drawable.help_24px, title = "TastyGo Refund Policy"),
            ProfileMenu(icon = R.drawable.help_24px, title = "Customer Support")
        )
    }


    val logoutSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var showLogoutSheet by rememberSaveable {
        mutableStateOf(false)
    }


    Scaffold { paddingValues ->
        AnimatedVisibility(
            visible = contentVisible,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 350,
                    easing = FastOutSlowInEasing
                )
            ) + slideInVertically(
                initialOffsetY = { 20 },
                animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
            )
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.background
                    ),
                contentPadding = PaddingValues(
                    bottom = paddingValues.calculateBottomPadding() + 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                item {

                    ProfileHeader(
                        profileImageUrl = profileImageUrl,
                        name = name,
                        phone = phone,
                        onEditProfile = onEditProfile,
                        isDarkTheme = isDarkTheme
                    )
                }

                item {

                    Text(
                        text = "Account",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 12.dp,
                            bottom = 2.dp
                        )
                    )
                }

                item {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        accountItems.forEach { item ->

                            ProfileMenuItem(
                                icon = item.icon,
                                title = item.title,
                                subtitle = item.subtitle,
                                modifier = Modifier.padding(
                                    horizontal = 20.dp
                                ),
                                onClick = {}
                            )
                        }
                    }
                }

                item {

                    Text(
                        text = "Feedback",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 12.dp,
                            bottom = 2.dp
                        )
                    )
                }

                item {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        feedbackItems.forEach { item ->

                            ProfileMenuItem(
                                icon = item.icon,
                                title = item.title,
                                subtitle = item.subtitle,
                                modifier = Modifier.padding(
                                    horizontal = 20.dp
                                ),
                                onClick = {}
                            )
                        }
                    }
                }

                item {

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    HorizontalDivider(
                        thickness = 0.5.dp
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    ProfileMenuItem(
                        icon = R.drawable.logout_24px,
                        title = "Logout",
                        subtitle = "Sign out from your account",
                        iconTint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(
                            horizontal = 20.dp
                        ),
                        onClick = {
                            showLogoutSheet = true
                        }//onLogout
                    )
                }
            }
        }
    }

    if (showLogoutSheet) {
        ModalBottomSheet(
            dragHandle = {},
            onDismissRequest = {
                showLogoutSheet = false
            },
            shape = RoundedCornerShape(12.dp),
            sheetState = logoutSheetState,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            LogoutConfirmationSheet(
                onCancel = {
                    showLogoutSheet = false
                },
                onLogout = {
                    showLogoutSheet = false

                    // Perform logout here
                    // viewModel.logout()
                }
            )
        }
    }

}


/* * ============================================================ * PROFILE HEADER * ============================================================ */

@Composable
private fun ProfileHeader(
    profileImageUrl: String?,
    name: String,
    phone: String,
    onEditProfile: () -> Unit,
    isDarkTheme: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = if (isDarkTheme) {
                    AppDarkGradient
                } else {
                    AppLightGradient
                }
            )
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Profile Avatar
            Box(
                modifier = Modifier.size(64.dp)
            ) {
                if (!profileImageUrl.isNullOrEmpty()) {
                    AsyncImage(
                        model = profileImageUrl,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.person_3_24px),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                // Edit button
                IconButton(
                    onClick = onEditProfile,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onBackground)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit_24px),
                        contentDescription = "Edit profile",
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(18.dp))

            // Name + Phone
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/* * ============================================================ * PROFILE MENU ITEM * ============================================================ */

@Composable
private fun ProfileMenuItem(
    icon: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /* * Icon container */

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = iconTint
                )
            }
            /* * Text */

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )
                if (subtitle.isNotBlank()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }
            /* * Chevron */
            Icon(
                painter =
                    painterResource(R.drawable.chevron_right_24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}