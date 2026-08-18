package com.delivery.fooddeliverycustomer.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    selectedCategory: String = "All",
    onSeeAllClick: () -> Unit,
    onCategoryClick: (FoodCategory?) -> Unit
) {

    // Add "All" as the first tab
    val allCategories = remember(categories) {
        listOf<FoodCategory?>(null) + categories
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            items(
                items = allCategories
            ) { category ->

                val categoryName =
                    category?.name ?: "All"

                val isSelected =
                    categoryName == selectedCategory

                CategoryTab(
                    title = categoryName,
                    selected = isSelected,
                    onClick = {
                        onCategoryClick(category)
                    }
                )
            }
        }

//        Spacer(
//            modifier = Modifier.height(4.dp)
//        )
    }
}


@Composable
private fun CategoryTab(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            },
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        // Selected indicator
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(
                    if (selected) 28.dp else 0.dp
                )
                .clip(
                    RoundedCornerShape(2.dp)
                )
                .background(
                    MaterialTheme.colorScheme.primary
                )
        )
    }
}