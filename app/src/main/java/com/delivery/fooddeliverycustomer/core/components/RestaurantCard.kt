package com.delivery.fooddeliverycustomer.core.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.PressInteraction.Press
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.theme.LightForeground
import com.delivery.fooddeliverycustomer.core.theme.Success
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant
import com.delivery.fooddeliverycustomer.presentation.components.cards.AppCard
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val cardWidth = screenWidth * 0.40f

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var showDeliveryTime by remember {
        mutableStateOf(false)
    }


    var isPressed by remember {
        mutableStateOf(false)
    }

    // Alternate between distance and delivery time
    LaunchedEffect(Unit) {
        while (true) {

            // Show distance first
            showDeliveryTime = false
            delay(2500.milliseconds)

            // Then show delivery time
            showDeliveryTime = true
            delay(2500.milliseconds)
        }
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is Press -> {
                    isPressed = true
                }

                is PressInteraction.Release,
                is PressInteraction.Cancel -> {
                    isPressed = false
                }
            }
        }
    }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = 0.55f,
            stiffness = 450f
        ),
        label = "restaurant_card_bounce"
    )

    AppCard(
        modifier = Modifier
            .width(cardWidth)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(
                RoundedCornerShape(12.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {

                        // Press down
                        isPressed = true

                        try {
                            awaitRelease()
                        } finally {
                            // Release
                            isPressed = false
                        }

                        // Give the bounce animation time to be visible
                        delay(100.milliseconds)

                        onClick()
                    }
                )
            }
    ) {

        Column {

            // Restaurant image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {

                AsyncImage(
                    model = restaurant.images.firstOrNull(),
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Favourite button
                /*
                IconButton(
                    onClick = onFavouriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(
                            MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.7f
                            )
                        )
                ) {
                    Icon(...)
                }
                */
            }


            Column(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
            ) {

                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // Your animated distance / delivery time
                AnimatedContent(
                    targetState = showDeliveryTime,
                    transitionSpec = {
                        if (targetState) {
                            slideInVertically(
                                initialOffsetY = { it }
                            ) togetherWith slideOutVertically(
                                targetOffsetY = { -it }
                            )
                        } else {
                            slideInVertically(
                                initialOffsetY = { it }
                            ) togetherWith slideOutVertically(
                                targetOffsetY = { -it }
                            )
                        }.using(
                            SizeTransform(clip = true)
                        )
                    },
                    label = "kitchen_info_animation"
                ) { deliveryTimeVisible ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Icon(
                            painter = painterResource(
                                if (deliveryTimeVisible) {
                                    R.drawable.nest_clock_farsight_analog_24px
                                } else {
                                    R.drawable.location_on_24px
                                }
                            ),
                            modifier = Modifier.size(16.dp),
                            tint = Success,
                            contentDescription = null
                        )

                        Text(
                            text = if (deliveryTimeVisible) {
                                "${restaurant.deliveryTime} min"
                            } else {
                                "${restaurant.distance} km"
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Success,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AllRestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(width = 0.5.dp, color = MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp,
        )
    ) {

        Column {

            // Restaurant image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {

                AsyncImage(
                    model = restaurant.logo,
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Favourite button
                IconButton(
                    onClick = onFavouriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(20.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(
                            MaterialTheme.colorScheme.onBackground.copy(
                                alpha = 0.7f
                            )
                        )
                ) {

//                    Icon(
//                        imageVector = if (restaurant.isFavourite) {
//                            Icons.Default.Favorite
//                        } else {
//                            Icons.Default.FavoriteBorder
//                        },
//                        contentDescription = "Favourite",
//                        tint = if (restaurant.isFavourite) {
//                            MaterialTheme.colorScheme.error
//                        } else {
//                            MaterialTheme.colorScheme.error
//                        }
//                    )
                }
            }


            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // Restaurant information
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Distance
//                    RestaurantInfoItem(
//                        icon = R.drawable.location_on_24px,
//                        text = restaurant.distance
//                    )
//
//                    // Offer
////                    RestaurantInfoItem(
////                        icon = R.drawable.percent_discount_24px,
////                        text = restaurant.offer
////                    )
//
//                    // Delivery time
//                    RestaurantInfoItem(
//                        icon = R.drawable.nest_clock_farsight_analog_24px,
//                        text = restaurant.deliveryTime
//                    )
                }
            }
        }
    }
}

@Composable
private fun RestaurantInfoItem(
    icon: Int,
    text: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(15.dp),
            tint = Success
        )

        Spacer(
            modifier = Modifier.width(4.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = Success,
            maxLines = 1
        )
    }
}

@Composable
private fun AnimatedGreenDivider(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(
        label = "divider_animation"
    )

    val highlightPosition by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 10800,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "highlight_position"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(3.dp)
    ) {

        val width = size.width
        val height = size.height

        // Green divider:
        // 0.5dp -> 3dp -> 0.5dp -> 3dp
        drawPath(
            path = Path().apply {
                moveTo(0f, height / 2f)

                cubicTo(
                    width * 0.20f,
                    height * 0.35f,
                    width * 0.30f,
                    height,
                    width * 0.50f,
                    height / 2f
                )

                cubicTo(
                    width * 0.70f,
                    0f,
                    width * 0.80f,
                    height * 0.65f,
                    width,
                    height / 2f
                )
            },
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color(0xFF4CAF50),
                    Color(0xFF4CAF50),
                    Color.Transparent
                )
            ),
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        )

        // Moving white highlight
        val highlightX = highlightPosition * width

        drawLine(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = 0.9f),
                    Color.Transparent
                ),
                startX = highlightX - width * 0.12f,
                endX = highlightX + width * 0.12f
            ),
            start = Offset(
                x = highlightX - width * 0.12f,
                y = height / 2f
            ),
            end = Offset(
                x = highlightX + width * 0.12f,
                y = height / 2f
            ),
            strokeWidth = 1.2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}