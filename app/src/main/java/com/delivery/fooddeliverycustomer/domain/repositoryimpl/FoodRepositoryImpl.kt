package com.delivery.fooddeliverycustomer.domain.repositoryimpl


import com.delivery.fooddeliverycustomer.core.common.Resource
import com.delivery.fooddeliverycustomer.data.local.dao.FoodItemDao
import com.delivery.fooddeliverycustomer.data.mapper.toDomain
import com.delivery.fooddeliverycustomer.data.mapper.toEntity
import com.delivery.fooddeliverycustomer.data.remote.api.FoodApi
import com.delivery.fooddeliverycustomer.data.repository.FoodRepository
import com.delivery.fooddeliverycustomer.domain.model.restaurant.FoodItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val dao: FoodItemDao,
    private val api: FoodApi
) : FoodRepository {

    override fun observeFoodItems(
        restaurantId: String
    ): Flow<Resource<List<FoodItem>>> {

        return dao.observeFoodItems(restaurantId)
            .map { entities ->
                Resource.Success(
                    entities.map { it.toDomain() }
                )
            }
    }

    override fun observeFoodItem(
        foodItemId: String
    ): Flow<Resource<FoodItem?>> {

        return dao.observeFoodItem(foodItemId)
            .map {
                Resource.Success(
                    it?.toDomain()
                )
            }
    }

    override fun observeFoodItemsByCategory(
        restaurantId: String,
        categoryId: String,
    ): Flow<Resource<List<FoodItem>>> {

        return dao.observeFoodByCategory(restaurantId,categoryId)
            .map { entities ->
                Resource.Success(
                    entities.map { it.toDomain() }
                )
            }
    }

    override fun searchFoodItems(
        restaurantId: String,
        query: String
    ): Flow<Resource<List<FoodItem>>> {

        return dao.searchFood(
            restaurantId,
            query
        ).map { entities ->
            Resource.Success(
                entities.map { it.toDomain() }
            )
        }
    }

//    override suspend fun refreshFoodItems(
//        restaurantId: String
//    ) {
//
//        val response =
//            api.getFoodItems(restaurantId)
//
//        dao.insertFoodItems(
//            response.map { it.toEntity() }
//        )
//    }
}