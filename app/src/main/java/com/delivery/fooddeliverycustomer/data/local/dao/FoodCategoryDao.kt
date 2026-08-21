package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.delivery.fooddeliverycustomer.data.local.entity.FoodCategoryEntity
import kotlinx.coroutines.flow.Flow



@Dao
interface FoodCategoryDao {

    @Query("""
        SELECT * FROM food_categories
        WHERE restaurantId = :restaurantId
        AND isActive = 1
        ORDER BY sortOrder ASC
    """)
    fun observeCategories(
        restaurantId: String
    ): Flow<List<FoodCategoryEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertCategories(
        categories: List<FoodCategoryEntity>
    )

    @Query("""
        DELETE FROM food_categories
        WHERE restaurantId = :restaurantId
    """)
    suspend fun deleteRestaurantCategories(
        restaurantId: String
    )
}