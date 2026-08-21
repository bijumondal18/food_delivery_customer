package com.delivery.fooddeliverycustomer.di

import com.delivery.fooddeliverycustomer.data.remote.api.AddressApi
import com.delivery.fooddeliverycustomer.data.remote.api.AuthApi
import com.delivery.fooddeliverycustomer.data.remote.api.CartApi
import com.delivery.fooddeliverycustomer.data.remote.api.FavouriteApi
import com.delivery.fooddeliverycustomer.data.remote.api.OrderApi
import com.delivery.fooddeliverycustomer.data.remote.api.RestaurantApi
import com.delivery.fooddeliverycustomer.domain.model.cart.Cart
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(
                "https://your-api.com/"
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi {

        return retrofit.create(
            AuthApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideRestaurantApi(
        retrofit: Retrofit
    ): RestaurantApi {

        return retrofit.create(
            RestaurantApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideAddressApi(
        retrofit: Retrofit
    ): AddressApi {

        return retrofit.create(
            AddressApi::class.java
        )
    }


    @Provides
    @Singleton
    fun provideCartApi(
        retrofit: Retrofit
    ): CartApi {

        return retrofit.create(
            CartApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideOrderApi(
        retrofit: Retrofit
    ): OrderApi {

        return retrofit.create(
            OrderApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideFavouriteApi(
        retrofit: Retrofit
    ): FavouriteApi {

        return retrofit.create(
            FavouriteApi::class.java
        )
    }
}