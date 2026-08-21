package com.delivery.fooddeliverycustomer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.delivery.fooddeliverycustomer.data.local.entity.OrderEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Query(
        """
        SELECT * FROM orders
        WHERE userId = :userId
        ORDER BY createdAt DESC
    """
    )
    fun observeOrders(
        userId: String
    ): Flow<List<OrderEntity>>

    @Query(
        """
        SELECT * FROM orders
        WHERE id = :orderId
        LIMIT 1
    """
    )
    fun observeOrder(
        orderId: String
    ): Flow<OrderEntity?>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertOrder(
        order: OrderEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertOrders(
        orders: List<OrderEntity>
    )

    @Update
    suspend fun updateOrder(
        order: OrderEntity
    )

    @Query(
        """
        SELECT * FROM orders
        WHERE userId = :userId
        AND orderStatus IN (
            'PLACED',
            'CONFIRMED',
            'RESTAURANT_PREPARING',
            'READY_FOR_PICKUP',
            'PICKED_UP',
            'OUT_FOR_DELIVERY'
        )
        ORDER BY createdAt DESC
        LIMIT 1
    """
    )
    fun observeActiveOrder(
        userId: String
    ): Flow<OrderEntity?>
}