package com.delivery.fooddeliverycustomer.di

import com.delivery.fooddeliverycustomer.data.repository.RestaurantRepository
import com.delivery.fooddeliverycustomer.domain.repositoryimpl.RestaurantRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRestaurantRepository(
        implementation: RestaurantRepositoryImpl
    ): RestaurantRepository

//    @Binds
//    @Singleton
//    abstract fun bindCartRepository(
//        implementation: CartRepositoryImpl
//    ): CartRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindOrderRepository(
//        implementation: OrderRepositoryImpl
//    ): OrderRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindAddressRepository(
//        implementation: AddressRepositoryImpl
//    ): AddressRepository
//
//    @Binds
//    @Singleton
//    abstract fun bindFavoriteRepository(
//        implementation: FavoriteRepositoryImpl
//    ): FavoriteRepository
}