package com.delivery.fooddeliverycustomer.presentation.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.theme.Success
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant
import com.delivery.fooddeliverycustomer.presentation.components.cards.AppCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllRestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = androidx.compose.animation.core.spring(
            dampingRatio = 0.65f,
            stiffness = 500f
        ),
        label = "restaurant_bounce"
    )

    /*
     * Keep the pager state local to this restaurant.
     *
     * The initial page is always 0.
     */
    val images = restaurant.images
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            images.size
        }
    )

    // ================================================================
    // AUTO SLIDE
    // ================================================================

    if (images.size > 1) {

        LaunchedEffect(
            restaurant.id,
            images.size
        ) {

            while (true) {

                delay(3000L)

                val nextPage =
                    (pagerState.currentPage + 1) % images.size

                pagerState.animateScrollToPage(
                    page = nextPage
                )
            }
        }
    }

    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {

        Column {

            // ========================================================
            // IMAGE SLIDER
            // ========================================================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {

                if (images.isNotEmpty()) {

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { page ->

                        AsyncImage(
                            model = images[page],
                            contentDescription = restaurant.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // =================================================
                    // PAGE INDICATOR
                    // =================================================

                    if (images.size > 1) {

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 10.dp, end = 10.dp),
                            horizontalArrangement = Arrangement.spacedBy(
                                5.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            images.forEachIndexed { index, _ ->

                                val isSelected =
                                    pagerState.currentPage == index

                                Box(
                                    modifier = Modifier
                                        .size(
                                            width = if (isSelected) {
                                                16.dp
                                            } else {
                                                6.dp
                                            },
                                            height = 6.dp
                                        )
                                        .graphicsLayer {
                                            alpha = if (isSelected) {
                                                1f
                                            } else {
                                                0.7f
                                            }
                                        }
                                        .background(
                                            color = androidx.compose.ui.graphics.Color.White,
                                            shape = CircleShape
                                        )
                                )
                            }
                        }
                    }

                } else {

                    // =================================================
                    // FALLBACK
                    // =================================================

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "No image",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // ========================================================
            // CONTENT
            // ========================================================

            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 12.dp
                )
            ) {

                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Icon(
                        painter = painterResource(
                            R.drawable.nest_clock_farsight_analog_24px
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Success
                    )

                    Text(
                        text = "${restaurant.deliveryTime} min",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Success
                    )

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    Icon(
                        painter = painterResource(
                            R.drawable.location_on_24px
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Success
                    )

                    Text(
                        text = "${restaurant.distance} km",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Success
                    )
                }
            }
        }
    }
}