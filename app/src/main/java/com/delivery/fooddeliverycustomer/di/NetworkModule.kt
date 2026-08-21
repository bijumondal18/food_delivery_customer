package com.delivery.fooddeliverycustomer.di

import com.delivery.fooddeliverycustomer.data.remote.api.RestaurantApi
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
    fun provideRestaurantApi(
        retrofit: Retrofit
    ): RestaurantApi {

        return retrofit.create(
            RestaurantApi::class.java
        )
    }
}