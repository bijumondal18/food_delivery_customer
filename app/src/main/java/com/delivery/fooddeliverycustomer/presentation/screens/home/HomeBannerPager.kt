package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.domain.model.home.Banner
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun HomeBannerPager(
    pagerState: PagerState
) {

    val banners = listOf(

        _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.home.Banner(
            id = "1",
            image = "https://images.unsplash.com/photo-1504674900247-0877df9cc836"
        ),

        _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.home.Banner(
            id = "2",
            image = "https://images.unsplash.com/photo-1513104890138-7c749659a591"
        ),

        _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.home.Banner(
            id = "3",
            image = "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38"
        ),

        _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.home.Banner(
            id = "4",
            image = "https://images.unsplash.com/photo-1555939594-58d7cb561ad1"
        )
    )


    LaunchedEffect(pagerState) {

        while (true) {

            delay(5000.milliseconds)

            val nextPage =
                (pagerState.currentPage + 1) % banners.size

            pagerState.animateScrollToPage(
                page = nextPage
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 16.dp
            )
    ) {

        HorizontalPager(
            state = pagerState,

            modifier = Modifier.fillMaxWidth(),

            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),

            pageSpacing = 10.dp
        ) { page ->

            BannerItem(
                banner = banners[page]
            )
        }

//        Spacer(
//            modifier = Modifier.height(10.dp)
//        )
//
//        BannerIndicator(
//            pagerState = pagerState,
//            banners = banners
//        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            banners.indices.forEach { index ->

                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(
                            width = if (
                                pagerState.currentPage == index
                            ) {
                                20.dp
                            } else {
                                7.dp
                            },
                            height = 7.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (
                                pagerState.currentPage == index
                            ) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outlineVariant
                            }
                        )
                )
            }
        }
    }
}