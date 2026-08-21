package com.delivery.fooddeliverycustomer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delivery.fooddeliverycustomer.data.local.dao.AddressDao
import com.delivery.fooddeliverycustomer.data.local.dao.CartDao
import com.delivery.fooddeliverycustomer.data.local.dao.FavoriteDao
import com.delivery.fooddeliverycustomer.data.local.dao.FoodCategoryDao
import com.delivery.fooddeliverycustomer.data.local.dao.FoodItemDao
import com.delivery.fooddeliverycustomer.data.local.dao.NotificationDao
import com.delivery.fooddeliverycustomer.data.local.dao.OrderDao
import com.delivery.fooddeliverycustomer.data.local.dao.RestaurantDao
import com.delivery.fooddeliverycustomer.data.local.dao.SearchHistoryDao
import com.delivery.fooddeliverycustomer.data.local.entity.AddressEntity
import com.delivery.fooddeliverycustomer.data.local.entity.CartEntity
import com.delivery.fooddeliverycustomer.data.local.entity.CartItemEntity
import com.delivery.fooddeliverycustomer.data.local.entity.CustomizationGroupEntity
import com.delivery.fooddeliverycustomer.data.local.entity.CustomizationOptionEntity
import com.delivery.fooddeliverycustomer.data.local.entity.FavoriteEntity
import com.delivery.fooddeliverycustomer.data.local.entity.FoodCategoryEntity
import com.delivery.fooddeliverycustomer.data.local.entity.FoodItemEntity
import com.delivery.fooddeliverycustomer.data.local.entity.NotificationEntity
import com.delivery.fooddeliverycustomer.data.local.entity.OrderEntity
import com.delivery.fooddeliverycustomer.data.local.entity.OrderItemEntity
import com.delivery.fooddeliverycustomer.data.local.entity.RestaurantEntity
import com.delivery.fooddeliverycustomer.data.local.entity.SearchHistoryEntity

@Database(
    entities = [
        RestaurantEntity::class,
        FoodCategoryEntity::class,
        FoodItemEntity::class,
        CustomizationGroupEntity::class,
        CustomizationOptionEntity::class,
        CartEntity::class,
        CartItemEntity::class,
        AddressEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        FavoriteEntity::class,
        SearchHistoryEntity::class,
        NotificationEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun foodCategoryDao(): FoodCategoryDao

    abstract fun foodItemDao(): FoodItemDao

    abstract fun cartDao(): CartDao

    abstract fun addressDao(): AddressDao

    abstract fun orderDao(): OrderDao

    abstract fun favoriteDao(): FavoriteDao

    abstract fun searchHistoryDao(): SearchHistoryDao

    abstract fun notificationDao(): NotificationDao
}