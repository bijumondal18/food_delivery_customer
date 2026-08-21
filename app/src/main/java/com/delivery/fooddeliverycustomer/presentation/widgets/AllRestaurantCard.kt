package com.delivery.fooddeliverycustomer.presentation.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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

@Composable
fun AllRestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit,
    onFavouriteClick: () -> Unit
) {

    var showDeliveryTime by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = 0.55f,
            stiffness = 450f
        ),
        label = "restaurant_card_scale"
    )

    /*
     * Alternate between distance and delivery time.
     *
     * The effect automatically gets cancelled when
     * this card leaves composition.
     */
    LaunchedEffect(restaurant.id) {

        while (true) {

            showDeliveryTime = false
            delay(2500)

            showDeliveryTime = true
            delay(2500)
        }
    }

    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(
                RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {

        Column {

            // Restaurant image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {

                AsyncImage(
                    model = restaurant.images.firstOrNull(),
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                /*
                Favourite button can be added here.
                */

            }

            Column(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
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

                AnimatedContent(
                    targetState = showDeliveryTime,
                    transitionSpec = {
                        (
                                slideInVertically(
                                    initialOffsetY = { it }
                                ) togetherWith
                                        slideOutVertically(
                                            targetOffsetY = { -it }
                                        )
                                ).using(
                                SizeTransform(clip = true)
                            )
                    },
                    label = "restaurant_info_animation"
                ) { deliveryTimeVisible ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Icon(
                            painter = painterResource(
                                id = if (deliveryTimeVisible) {
                                    R.drawable.nest_clock_farsight_analog_24px
                                } else {
                                    R.drawable.location_on_24px
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Success
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