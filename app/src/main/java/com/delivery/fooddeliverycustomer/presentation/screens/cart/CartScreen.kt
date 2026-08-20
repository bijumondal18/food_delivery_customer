package com.delivery.fooddeliverycustomer.presentation.cart


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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R

data class CartItem(
    val name: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    items: List<CartItem> = emptyList(),
    onCheckout: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {

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
                        text = "Your Cart"
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

            if (items.isEmpty()) {

                EmptyCart(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )

            } else {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                ) {

                    items.forEach { item ->

                        CartItemCard(
                            item = item
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    PriceSummary()

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Button(
                        onClick = onCheckout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.primary
                        )
                    ) {

                        Text(
                            text = "Proceed to Checkout"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyCart(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
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
                painter = painterResource(R.drawable.shopping_cart_24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Your cart is empty",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Looks like you haven't added anything yet.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CartItemCard(
    item: CartItem
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "₹${item.price}",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {}
                ) {

                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Decrease"
                    )
                }

                Text(
                    text = item.quantity.toString()
                )

                IconButton(
                    onClick = {}
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase"
                    )
                }
            }
        }
    }
}

@Composable
private fun PriceSummary() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            PriceRow(
                title = "Subtotal",
                value = "₹420"
            )

            PriceRow(
                title = "Delivery fee",
                value = "₹40"
            )

            PriceRow(
                title = "Taxes",
                value = "₹24"
            )

            PriceRow(
                title = "Total",
                value = "₹484",
                isTotal = true
            )
        }
    }
}

@Composable
private fun PriceRow(
    title: String,
    value: String,
    isTotal: Boolean = false
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            fontWeight =
                if (isTotal) androidx.compose.ui.text.font.FontWeight.Bold
                else androidx.compose.ui.text.font.FontWeight.Normal
        )

        Text(
            text = value,
            fontWeight =
                if (isTotal) androidx.compose.ui.text.font.FontWeight.Bold
                else androidx.compose.ui.text.font.FontWeight.Normal,
            color =
                if (isTotal)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface
        )
    }
}