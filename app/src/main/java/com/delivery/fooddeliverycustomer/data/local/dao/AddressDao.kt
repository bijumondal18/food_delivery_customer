package com.delivery.fooddeliverycustomer.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.delivery.fooddeliverycustomer.data.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AddressDao {

    @Query(
        """
        SELECT * FROM addresses
        WHERE userId = :userId
        ORDER BY isDefault DESC
    """
    )
    fun observeAddresses(
        userId: String
    ): Flow<List<AddressEntity>>

    @Query(
        """
        SELECT * FROM addresses
        WHERE userId = :userId AND isDefault = 1
        LIMIT 1
    """
    )
    fun observeDefaultAddress(
        userId: String
    ): Flow<AddressEntity?>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAddress(
        address: AddressEntity
    )

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAddresses(
        addresses: List<AddressEntity>
    )

    @Update
    suspend fun updateAddress(
        address: AddressEntity
    )

    @Delete
    suspend fun deleteAddress(
        address: AddressEntity
    )

    @Query("DELETE FROM addresses WHERE id = :id")
    suspend fun deleteAddress(
        id: String
    )

    @Query("DELETE FROM addresses WHERE userId = :userId")
    suspend fun deleteAllAddressesByUserId(
        userId: String
    )

    @Transaction
    suspend fun replaceAddresses(
        userId: String,
        addresses: List<AddressEntity>
    ) {
        deleteAllAddressesByUserId(userId)
        insertAddresses(addresses)
    }

    @Query(
        """
        UPDATE addresses
        SET isDefault = 0
        WHERE userId = :userId
    """
    )
    suspend fun clearDefaultAddress(
        userId: String
    )

    @Query(
        """
        UPDATE addresses
        SET isDefault = 1
        WHERE id = :addressId
    """
    )
    suspend fun setDefaultAddress(
        addressId: String
    )
}