package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery.fooddeliverycustomer.data.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("""
        SELECT * FROM search_history
        WHERE userId = :userId
        ORDER BY createdAt DESC
        LIMIT 10
    """)
    fun observeHistory(
        userId: String
    ): Flow<List<SearchHistoryEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        history: SearchHistoryEntity
    )

    @Query("""
        DELETE FROM search_history
        WHERE userId = :userId
    """)
    suspend fun clearHistory(
        userId: String
    )
}