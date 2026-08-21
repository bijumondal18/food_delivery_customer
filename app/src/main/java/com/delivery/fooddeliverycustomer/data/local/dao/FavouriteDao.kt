package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery.fooddeliverycustomer.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Query("""
        SELECT * FROM favorites
        WHERE userId = :userId
        AND type = :type
    """)
    fun observeFavorites(
        userId: String,
        type: String
    ): Flow<List<FavoriteEntity>>

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM favorites
            WHERE userId = :userId
            AND itemId = :itemId
            AND type = :type
        )
    """)
    fun isFavorite(
        userId: String,
        itemId: String,
        type: String
    ): Flow<Boolean>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertFavorite(
        favorite: FavoriteEntity
    )

    @Query("""
        DELETE FROM favorites
        WHERE userId = :userId
        AND itemId = :itemId
        AND type = :type
    """)
    suspend fun deleteFavorite(
        userId: String,
        itemId: String,
        type: String
    )
}