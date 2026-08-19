package com.delivery.fooddeliverycustomer.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.lerp
import kotlinx.coroutines.flow.Flow

 data class ProfileMenu(
    val icon: Int,
    val title: String,
    val subtitle: String = "",
    val iconColor: Color? = null,
    val iconBackground: Color? = null
)

 data class ProfileQuickAction(
    val icon: Int,
    val title: String,
    val description: String
)

private val TOP_BAR_HEIGHT = 64.dp
private const val COLLAPSE_DISTANCE = 180

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileImageUrl: String? = null,
    name: String = "Biju Mondal",
    phone: String = "+91 98765 43210",
    onEditProfile: () -> Unit = {},
    onLogout: () -> Unit = {},
    onBackClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onCartClick: () -> Unit,
    onWishlistClick: () -> Unit,
) {
    val listState = rememberLazyListState()

    /*
     * This is the amount of the profile header that has been scrolled.
     *
     * 0f = completely expanded
     * 1f = completely collapsed
     */
    val progress by remember {
        derivedStateOf {
            val scroll = when {
                listState.firstVisibleItemIndex > 0 -> COLLAPSE_DISTANCE
                else -> listState.firstVisibleItemScrollOffset
            }

            (scroll / COLLAPSE_DISTANCE.toFloat())
                .coerceIn(0f, 1f)
        }
    }

    val isDarkTheme = isSystemInDarkTheme()

    var contentVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        contentVisible = true
    }

    val accountItems = remember {
        listOf(
            ProfileMenu(
                R.drawable.person_edit_24px,
                "Edit Profile",
                "Update your personal information",
                iconColor = Color(0xFF4CAF50),
                iconBackground = Color(0xFFE8F5E9)
            ),
            ProfileMenu(
                R.drawable.location_on_24px,
                "Saved Addresses",
                "Manage your delivery addresses",
                iconColor = Color(0xFF2196F3),
                iconBackground = Color(0xFFE3F2FD)
            ),
            ProfileMenu(
                R.drawable.notifications_24px,
                "Notifications",
                "Manage notification preferences",
                iconColor = Color(0xFF9C27B0),
                iconBackground = Color(0xFFF3E5F5)
            ),
            ProfileMenu(
                R.drawable.settings_24px,
                "Settings",
                "App preferences",
                iconColor = Color(0xFFFF9800),
                iconBackground = Color(0xFFFFF3E0)
            ),
        )
    }

    val feedbackItems = remember {
        listOf(
            ProfileMenu(
                R.drawable.article_24px,
                "Terms & Conditions",
                iconColor = Color(0xFF607D8B),
                iconBackground = Color(0xFFECEFF1)
            ),
            ProfileMenu(
                R.drawable.policy_24px,
                "Privacy Policy",
                iconColor = Color(0xFF3F51B5),
                iconBackground = Color(0xFFE8EAF6)
            ),
            ProfileMenu(
                R.drawable.privacy_tip_24px,
                "Restaurant Partner Terms & Conditions",
                iconColor = Color(0xFF795548),
                iconBackground = Color(0xFFEFEBE9)
            ),
            ProfileMenu(
                R.drawable.demography_24px,
                "Khabo Refund Policy",
                iconColor = Color(0xFF009688),
                iconBackground = Color(0xFFE0F2F1)
            ),
            ProfileMenu(
                R.drawable.headset_mic_24px,
                "Customer Support",
                iconColor = Color(0xFFFF5722),
                iconBackground = Color(0xFFFBE9E7)
            )
        )
    }

     data class MenuColors(
        val icon: Color,
        val background: Color
    )

    @Composable
     fun profileMenuColors(): List<MenuColors> {
        return listOf(
            MenuColors(
                icon = Color(0xFF4CAF50),
                background = Color(0xFFE8F5E9)
            ),
            MenuColors(
                icon = Color(0xFF2196F3),
                background = Color(0xFFE3F2FD)
            ),
            MenuColors(
                icon = Color(0xFF9C27B0),
                background = Color(0xFFF3E5F5)
            ),
            MenuColors(
                icon = Color(0xFFFF9800),
                background = Color(0xFFFFF3E0)
            ),
            MenuColors(
                icon = Color(0xFFE91E63),
                background = Color(0xFFFCE4EC)
            )
        )
    }

    val quickActions = remember {
        listOf(
            ProfileQuickAction(
                icon = R.drawable.shopping_bag_24px,
                title = "My Orders",
                description = "Track your orders"
            ),
            ProfileQuickAction(
                icon = R.drawable.favorite_24px,
                title = "Wishlist",
                description = "Your saved items"
            ),
            ProfileQuickAction(
                icon = R.drawable.shopping_cart_24px,
                title = "My Cart",
                description = "Items in your cart"
            )
        )
    }


    var showLogoutSheet by rememberSaveable {
        mutableStateOf(false)
    }

    val logoutSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                ),
            contentPadding = PaddingValues(
                top = TOP_BAR_HEIGHT,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item {

                ProfileHeaderSpace(
                    isDarkTheme = isDarkTheme
                )
            }


            item {

                Spacer(modifier = Modifier.height(16.dp))

                ProfileQuickActions(
                    items = quickActions,
                    onOrdersClick = onOrdersClick,
                    onWishlistClick = onWishlistClick,
                    onCartClick = onCartClick
                )
            }


            item {

                Text(
                    text = "Account",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
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
                            iconTint = item.iconColor,
                            iconBackground = item.iconBackground,
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
                    color = MaterialTheme.colorScheme.onBackground,
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
                            iconTint = item.iconColor,
                            iconBackground = item.iconBackground,
                            onClick = {}
                        )
                    }
                }
            }

            item {

                HorizontalDivider(
                    modifier = Modifier.padding(
                        vertical = 4.dp
                    ),
                    thickness = 0.5.dp
                )

                ProfileMenuItem(
                    icon = R.drawable.logout_24px,
                    title = "Logout",
                    subtitle = "Sign out from your account",
                    iconTint = MaterialTheme.colorScheme.primary,
                    onClick = {
                        showLogoutSheet = true
                    }
                )
            }
        }

        ProfileTopBar(
            onBackClick = onBackClick,
            progress = progress
        )

        ProfileAnimatedHeader(
            profileImageUrl = profileImageUrl,
            name = name,
            phone = phone,
            progress = progress,
            onEditProfile = onEditProfile,
            isDarkTheme = isDarkTheme
        )
    }

    if (showLogoutSheet) {

        ModalBottomSheet(
            dragHandle = {},
            onDismissRequest = {
                showLogoutSheet = false
            },
            shape = RoundedCornerShape(16.dp),
            sheetState = logoutSheetState,
            containerColor = MaterialTheme.colorScheme.surface
        ) {

            LogoutConfirmationSheet(

                onCancel = {
                    showLogoutSheet = false
                },

                onLogout = {
                    showLogoutSheet = false
                    onLogout()
                }
            )
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
    iconTint: Color? = null,
    iconBackground: Color? = null,
    onClick: () -> Unit
) {

    val finalIconColor =
        iconTint ?: MaterialTheme.colorScheme.primary

    val finalIconBackground =
        iconBackground
            ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(finalIconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = finalIconColor
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (subtitle.isNotBlank()) {
                    Text(
                        text = subtitle,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Icon(
                painter =
                    painterResource(R.drawable.chevron_right_24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Composable
private fun ProfileAvatar(
    profileImageUrl: String?,
    size: Dp,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
    ) {

        if (!profileImageUrl.isNullOrEmpty()) {

            AsyncImage(
                model = profileImageUrl,
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        } else {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.primary
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(
                        R.drawable.person_3_24px
                    ),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(
                        size * 0.48f
                    )
                )
            }
        }
    }
}


@Composable
private fun ProfileHeaderSpace(
    isDarkTheme: Boolean
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    )
}

@Composable
private fun ProfileAnimatedHeader(
    profileImageUrl: String?,
    name: String,
    phone: String,
    progress: Float,
    onEditProfile: () -> Unit,
    isDarkTheme: Boolean
) {


    val avatarSize = lerp(
        92.dp,
        38.dp,
        progress
    )


    val avatarX = lerp(
        16.dp,
        56.dp,
        progress
    )

    val avatarY = lerp(
        70.dp,
        9.dp,
        progress
    )


    val nameX = lerp(
        126.dp,
        104.dp,
        progress
    )

    val nameY = lerp(
        86.dp,
        17.dp,
        progress
    )

    val nameSize = lerp(
        22.sp,
        17.sp,
        progress
    )

    val phoneAlpha = 1f - (progress * 2f).coerceIn(0f, 1f)


    val editSize = lerp(
        32.dp,
        16.dp,
        progress
    )

    val editX = lerp(
        76.dp,
        42.dp,
        progress
    )

    val editY = lerp(
        130.dp,
        34.dp,
        progress
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        ProfileAvatar(
            profileImageUrl = profileImageUrl,
            size = avatarSize,
            modifier = Modifier
                .graphicsLayer {
                    translationX = avatarX.toPx()
                    translationY = avatarY.toPx()
                }
        )

        Box(
            modifier = Modifier
                .size(editSize)
                .graphicsLayer {
                    translationX = editX.toPx()
                    translationY = editY.toPx()
                    alpha = 1f - progress
                }
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.secondary
                )
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .clickable(
                    onClick = onEditProfile
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(
                    R.drawable.edit_24px
                ),
                contentDescription = "Edit profile",
                tint = Color.Black,
                modifier = Modifier.size(
                    editSize * 0.45f
                )
            )
        }

        Column(
            modifier = Modifier
                .graphicsLayer {
                    translationX = nameX.toPx()
                    translationY = nameY.toPx()
                }
        ) {

            Text(
                text = name,
                fontSize = nameSize,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = phone,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.graphicsLayer {
                    alpha = phoneAlpha
                },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileTopBar(
    onBackClick: () -> Unit,
    progress: Float
) {

    val density = LocalDensity.current

    val statusBarHeight = with(density) {
        WindowInsets.statusBars.getTop(this).toDp()
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT + statusBarHeight),
        color = MaterialTheme.colorScheme.background.copy(
            alpha = progress.coerceIn(0f, 1f)
        ),
        tonalElevation = lerp(0.dp, 2.dp, progress),
        shadowElevation = lerp(0.dp, 0.5.dp, progress)
    ) {

        TopAppBar(

            navigationIcon = {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .statusBarsPadding()
                            .offset(y = (-3).dp)
                            .align(Alignment.CenterStart)
                    ) {

                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            },

            title = {
                // Empty intentionally.
            },

            colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),

            modifier = Modifier.statusBarsPadding()
        )
    }
}


@Composable
private fun ProfileQuickActions(
    items: List<ProfileQuickAction>,
    onOrdersClick: () -> Unit,
    onWishlistClick: () -> Unit,
    onCartClick: () -> Unit
) {

    val clickActions = listOf(
        onOrdersClick,
        onWishlistClick,
        onCartClick
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 24.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items.forEachIndexed { index, item ->

            ProfileQuickActionItem(
                item = item,
                modifier = Modifier.weight(1f),
                onClick = clickActions[index]
            )
        }
    }
}

