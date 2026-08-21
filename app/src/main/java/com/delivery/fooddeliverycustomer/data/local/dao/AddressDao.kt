package com.delivery.fooddeliverycustomer.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.delivery.fooddeliverycustomer.data.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AddressDao {

    @Query("""
        SELECT * FROM addresses
        WHERE userId = :userId
        ORDER BY isDefault DESC
    """)
    fun observeAddresses(
        userId: String
    ): Flow<List<AddressEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAddress(
        address: AddressEntity
    )

    @Update
    suspend fun updateAddress(
        address: AddressEntity
    )

    @Delete
    suspend fun deleteAddress(
        address: AddressEntity
    )

    @Query("""
        UPDATE addresses
        SET isDefault = 0
        WHERE userId = :userId
    """)
    suspend fun clearDefaultAddress(
        userId: String
    )

    @Query("""
        UPDATE addresses
        SET isDefault = 1
        WHERE id = :addressId
    """)
    suspend fun setDefaultAddress(
        addressId: String
    )
}