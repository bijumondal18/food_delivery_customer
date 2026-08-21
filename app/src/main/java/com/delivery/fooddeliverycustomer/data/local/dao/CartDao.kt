package com.delivery.fooddeliverycustomer.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.delivery.fooddeliverycustomer.data.local.entity.CartEntity
import com.delivery.fooddeliverycustomer.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Transaction
    @Query(
        """
        SELECT * FROM carts
        WHERE userId = :userId
        LIMIT 1
    """
    )
    fun observeCart(
        userId: String
    ): Flow<CartEntity?>

    @Transaction
    @Query(
        """
        SELECT * FROM carts
        WHERE userId = :userId
        LIMIT 1
    """
    )
    suspend fun getCart(
        userId: String
    ): CartEntity?

    @Query(
        """
        SELECT * FROM cart_items
        WHERE cartId = :cartId
        AND foodItemId = :foodItemId
        LIMIT 1
    """
    )
    suspend fun getCartItem(
        cartId: String,
        foodItemId: String
    ): CartItemEntity?

    @Query(
        """
        SELECT * FROM cart_items
        WHERE cartId = :cartId
    """
    )
    fun observeCartItems(
        cartId: String
    ): Flow<List<CartItemEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertCart(
        cart: CartEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertCartItem(
        item: CartItemEntity
    )

    @Update
    suspend fun updateCartItem(
        item: CartItemEntity
    )

    @Query(
        """
        UPDATE cart_items
        SET quantity = :quantity,
            totalPrice = :totalPrice
        WHERE cartId = :cartId
        AND foodItemId = :foodItemId
    """
    )
    suspend fun updateQuantity(
        cartId: String,
        foodItemId: String,
        quantity: Int,
        totalPrice: Double
    )

    @Query(
        """
        DELETE FROM cart_items
        WHERE cartId = :cartId
        AND foodItemId = :foodItemId
    """
    )
    suspend fun deleteCartItem(
        cartId: String,
        foodItemId: String
    )

    @Query(
        """
        DELETE FROM cart_items
        WHERE cartId = :cartId
    """
    )
    suspend fun clearCartItems(
        cartId: String
    )

    @Query(
        """
        DELETE FROM carts
        WHERE userId = :userId
    """
    )
    suspend fun clearCart(
        userId: String
    )

    @Query(
        """
        UPDATE carts
        SET syncStatus = :status
        WHERE id = :cartId
    """
    )
    suspend fun updateSyncStatus(
        cartId: String,
        status: String
    )
}