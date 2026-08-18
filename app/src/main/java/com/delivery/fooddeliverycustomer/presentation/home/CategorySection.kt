package com.delivery.fooddeliverycustomer.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.core.components.FoodCategoryItem
import com.delivery.fooddeliverycustomer.data.model.FoodCategory

@Composable
fun CategorySection(
    categories: List<FoodCategory>,
    onSeeAllClick: () -> Unit,
    onCategoryClick: (FoodCategory) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp
            )
    ) {

        // --------------------------------------------------
        // Section Header
        // --------------------------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Browse by Categories",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "See All",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        onSeeAllClick()
                    }
                    .padding(
                        horizontal = 8.dp,
                        vertical = 6.dp
                    )
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // --------------------------------------------------
        // Horizontal Categories
        // --------------------------------------------------

        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = categories,
                key = { it.name }
            ) { category ->

                FoodCategoryItem(
                    category = category,
                    onClick = {
                        onCategoryClick(category)
                    }
                )
            }
        }
    }
}