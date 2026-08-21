package com.delivery.fooddeliverycustomer.core.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.theme.Success
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecommendedRestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit
) {

    var showDeliveryTime by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var isPressed by remember {
        mutableStateOf(false)
    }


    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.94f else 1f,
        animationSpec = spring(
            dampingRatio = 0.45f,
            stiffness = 450f
        ),
        label = "card_bounce"
    )

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

    Column(
        modifier = Modifier
            .width(130.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
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

        // Image + rating
        Box(
            modifier = Modifier
                .width(130.dp)
                .height(112.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            topEnd = 12.dp,
                            bottomEnd = 12.dp,
                            bottomStart = 20.dp
                        )
                    )
            ) {

                AsyncImage(
                    model = restaurant.images.firstOrNull(),
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            // Rating
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 18.dp,
                            bottomEnd = 18.dp
                        )
                    )
                    .background(Color.White)
                    .padding(
                        top = 4.dp,
                        end = 6.dp,
                        bottom = 4.dp
                    )
            ) {

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Success)
                        .padding(
                            horizontal = 5.dp,
                            vertical = 3.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Icon(
                        painter = painterResource(R.drawable.star_24px),
                        contentDescription = "Rating",
                        modifier = Modifier.size(12.dp),
                        tint = Color.White
                    )

                    Text(
                        text = "${restaurant.rating}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.height(4.dp)
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