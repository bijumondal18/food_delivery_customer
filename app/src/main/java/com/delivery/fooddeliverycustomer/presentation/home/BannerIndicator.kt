package com.delivery.fooddeliverycustomer.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.data.model.HomeBanner

@Composable
fun BannerIndicator(
    pagerState: PagerState,
    banners: List<HomeBanner>
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        banners.indices.forEach { index ->

            val selected =
                pagerState.currentPage == index

            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(
                        width = if (selected) {
                            20.dp
                        } else {
                            7.dp
                        },
                        height = 7.dp
                    )
                    .clip(CircleShape)
                    .background(
                        if (selected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        }
                    )
            )
        }
    }
}