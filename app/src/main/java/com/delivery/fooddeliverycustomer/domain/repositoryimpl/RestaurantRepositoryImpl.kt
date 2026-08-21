package com.delivery.fooddeliverycustomer.domain.repositoryimpl

import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.data.local.dao.RestaurantDao
import com.delivery.fooddeliverycustomer.data.mapper.toDomain
import com.delivery.fooddeliverycustomer.data.mapper.toEntity
import com.delivery.fooddeliverycustomer.data.remote.api.RestaurantApi
import com.delivery.fooddeliverycustomer.data.repository.RestaurantRepository
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class RestaurantRepositoryImpl @Inject constructor(
    private val dao: RestaurantDao,
    private val api: RestaurantApi
) : RestaurantRepository {

    override fun observeRestaurants():
            Flow<Resource<List<Restaurant>>> {

        return dao.observeRestaurants()
            .map { entities ->

                Resource.Success(
                    entities.map {
                        it.toDomain()
                    }
                )
            }
    }

    override fun observeRestaurant(
        id: String
    ): Flow<Resource<Restaurant?>> {

        return dao.observeRestaurant(id)
            .map { entity ->

                Resource.Success(
                    entity?.toDomain()
                )
            }
    }

    override fun searchRestaurants(
        query: String
    ): Flow<Resource<List<Restaurant>>> {

        return dao.searchRestaurants(query)
            .map { entities ->

                Resource.Success(
                    entities.map {
                        it.toDomain()
                    }
                )
            }
    }

    override suspend fun refreshRestaurants() {

        val response =
            api.getRestaurants()

        dao.insertRestaurants(
            response.map {
                it.toEntity()
            }
        )
    }
}