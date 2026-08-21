package com.delivery.fooddeliverycustomer.presentation.screens.order


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.domain.model.order.Order


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    orders: List<Order> = emptyList(),
    onBackClick: () -> Unit
) {

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    var contentVisible by remember { mutableStateOf(false) }
    /* * Trigger the screen entrance animation only once. */

    LaunchedEffect(Unit) {
        contentVisible = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        text = "My Orders"
                    )
                }
            )
        }

    ) { paddingValues ->

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
                    .padding(paddingValues)
            ) {

                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.background,
                    divider = {
                        HorizontalDivider(
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.outlineVariant.copy(
                                alpha = 0.5f
                            )
                        )
                    }
                ) {

                    Tab(
                        selected = selectedTab == 0,
                        onClick = {
                            selectedTab = 0
                        },
                        text = {
                            Text("Active")
                        }
                    )

                    Tab(
                        selected = selectedTab == 1,
                        onClick = {
                            selectedTab = 1
                        },
                        text = {
                            Text("Past Orders")
                        }
                    )
                }

                if (orders.isEmpty()) {

                    EmptyOrders()

                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(12.dp)
                    ) {

                        orders.forEach { order ->

                            OrderCard(
                                order = order
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyOrders() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.10f
                    )
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(R.drawable.order_approve_24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "No orders yet",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Your delicious journey starts here.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OrderCard(
    order: Order
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.10f
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(R.drawable.order_approve_24px),
                        contentDescription = null,
                        tint =
                            MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                ) {

                    Text(
                        text = order.restaurantName,
                        fontWeight = FontWeight.Bold
                    )

//                    Text(
//                        text = order.updatedAt,
//                        style =
//                            MaterialTheme.typography.bodySmall,
//                        color =
//                            MaterialTheme.colorScheme.onSurfaceVariant
//                    )
                }

                StatusBadge(
                    status = order.orderStatus.name
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

//            Text(
//                text = order.items,
//                style = MaterialTheme.typography.bodyMedium
//            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Order #${order.id}",
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

//                Text(
//                    text = order.totalAmount,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.primary
//                )
            }
        }
    }
}

@Composable
private fun StatusBadge(
    status: String
) {

    val isCompleted =
        status.equals(
            "Delivered",
            ignoreCase = true
        )

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (isCompleted)
                    Color(0xFFE7F7ED)
                else
                    Color(0xFFFFF3D6)
            )
            .padding(
                horizontal = 10.dp,
                vertical = 6.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(
                if (isCompleted)
                    R.drawable.check_circle_24px
                else
                    R.drawable.schedule_24px
            ),
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint =
                if (isCompleted)
                    Color(0xFF208B4A)
                else
                    Color(0xFFB47700)
        )

        Spacer(
            modifier = Modifier.size(4.dp)
        )

        Text(
            text = status,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}