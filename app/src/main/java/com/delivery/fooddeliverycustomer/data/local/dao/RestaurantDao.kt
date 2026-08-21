package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery.fooddeliverycustomer.data.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RestaurantDao {

    @Query("""
        SELECT * FROM restaurants
        ORDER BY rating DESC
    """)
    fun observeRestaurants():
            Flow<List<RestaurantEntity>>

    @Query("""
        SELECT * FROM restaurants
        WHERE id = :restaurantId
        LIMIT 1
    """)
    fun observeRestaurant(
        restaurantId: String
    ): Flow<RestaurantEntity?>

    @Query("""
        SELECT * FROM restaurants
        WHERE name LIKE '%' || :query || '%'
        ORDER BY rating DESC
    """)
    fun searchRestaurants(
        query: String
    ): Flow<List<RestaurantEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRestaurants(
        restaurants: List<RestaurantEntity>
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRestaurant(
        restaurant: RestaurantEntity
    )

    @Delete
    suspend fun deleteRestaurant(
        restaurant: RestaurantEntity
    )

    @Query("DELETE FROM restaurants")
    suspend fun clearRestaurants()
}