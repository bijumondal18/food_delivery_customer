package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delivery.fooddeliverycustomer.data.local.entity.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Query("""
        SELECT * FROM notifications
        WHERE userId = :userId
        ORDER BY createdAt DESC
    """)
    fun observeNotifications(
        userId: String
    ): Flow<List<NotificationEntity>>

    @Query("""
        SELECT COUNT(*) FROM notifications
        WHERE userId = :userId
        AND isRead = 0
    """)
    fun observeUnreadCount(
        userId: String
    ): Flow<Int>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        notification: NotificationEntity
    )

    @Query("""
        UPDATE notifications
        SET isRead = 1
        WHERE id = :notificationId
    """)
    suspend fun markAsRead(
        notificationId: String
    )

    @Query("""
        UPDATE notifications
        SET isRead = 1
        WHERE userId = :userId
    """)
    suspend fun markAllAsRead(
        userId: String
    )
}