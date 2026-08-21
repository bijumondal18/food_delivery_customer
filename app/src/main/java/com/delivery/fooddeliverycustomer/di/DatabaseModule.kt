package com.delivery.fooddeliverycustomer.di

import android.content.Context
import androidx.room.Room
import com.delivery.fooddeliverycustomer.data.local.dao.AddressDao
import com.delivery.fooddeliverycustomer.data.local.dao.CartDao
import com.delivery.fooddeliverycustomer.data.local.dao.FavoriteDao
import com.delivery.fooddeliverycustomer.data.local.dao.FoodCategoryDao
import com.delivery.fooddeliverycustomer.data.local.dao.FoodItemDao
import com.delivery.fooddeliverycustomer.data.local.dao.NotificationDao
import com.delivery.fooddeliverycustomer.data.local.dao.OrderDao
import com.delivery.fooddeliverycustomer.data.local.dao.RestaurantDao
import com.delivery.fooddeliverycustomer.data.local.dao.SearchHistoryDao
import com.delivery.fooddeliverycustomer.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "food_delivery_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRestaurantDao(
        database: AppDatabase
    ): RestaurantDao {
        return database.restaurantDao()
    }

    @Provides
    fun provideFoodCategoryDao(
        database: AppDatabase
    ): FoodCategoryDao {
        return database.foodCategoryDao()
    }

    @Provides
    fun provideFoodItemDao(
        database: AppDatabase
    ): FoodItemDao {
        return database.foodItemDao()
    }

    @Provides
    fun provideCartDao(
        database: AppDatabase
    ): CartDao {
        return database.cartDao()
    }

    @Provides
    fun provideAddressDao(
        database: AppDatabase
    ): AddressDao {
        return database.addressDao()
    }

    @Provides
    fun provideOrderDao(
        database: AppDatabase
    ): OrderDao {
        return database.orderDao()
    }

    @Provides
    fun provideFavoriteDao(
        database: AppDatabase
    ): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    fun provideSearchHistoryDao(
        database: AppDatabase
    ): SearchHistoryDao {
        return database.searchHistoryDao()
    }

    @Provides
    fun provideNotificationDao(
        database: AppDatabase
    ): NotificationDao {
        return database.notificationDao()
    }
}