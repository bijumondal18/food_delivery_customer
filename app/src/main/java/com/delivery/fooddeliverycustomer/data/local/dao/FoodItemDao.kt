package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.delivery.fooddeliverycustomer.data.local.entity.FoodItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodItemDao {

    @Query(
        """
        SELECT * FROM food_items
        WHERE restaurantId = :restaurantId
        AND isAvailable = 1
    """
    )
    fun observeFoodItems(
        restaurantId: String
    ): Flow<List<FoodItemEntity>>

    @Query(
        """
        SELECT * FROM food_items
        WHERE restaurantId = :restaurantId
        AND categoryId = :categoryId
        AND isAvailable = 1
    """
    )
    fun observeFoodByCategory(
        restaurantId: String,
        categoryId: String
    ): Flow<List<FoodItemEntity>>

    @Query(
        """
        SELECT * FROM food_items
        WHERE name LIKE '%' || :query || '%'
        AND isAvailable = 1
    """
    )
    fun searchFood(
        query: String
    ): Flow<List<FoodItemEntity>>

    @Query(
        """
        SELECT * FROM food_items
        WHERE id = :foodItemId
        LIMIT 1
    """
    )
    fun observeFoodItem(
        foodItemId: String
    ): Flow<FoodItemEntity?>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertFoodItems(
        items: List<FoodItemEntity>
    )

    @Query(
        """
        DELETE FROM food_items
        WHERE restaurantId = :restaurantId
    """
    )
    suspend fun deleteRestaurantFood(
        restaurantId: String
    )
}