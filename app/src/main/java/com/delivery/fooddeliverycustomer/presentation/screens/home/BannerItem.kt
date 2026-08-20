package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.data.model.home.Banner

@Composable
fun BannerItem(
    banner: Banner
) {

    AsyncImage(
        model = banner.image,
        contentDescription = null,

        contentScale = ContentScale.Crop,

        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.1f)
            .clip(
                RoundedCornerShape(18.dp)
            )
    )
}