package com.bedirhandroid.simpleecommerceapp.network.usecases.product.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductUseCaseImpl @Inject constructor(private val repo: Repo) : ProductUseCase {
    override suspend fun getProducts(): Flow<PagingData<ProductResponseUi>> {
        return repo.getProducts().map { pagingData ->
            pagingData.map { productData ->
                ProductResponseUi(
                    id = productData.id.toString(),
                    title = productData.title,
                    price = productData.price,
                    description = productData.description,
                    image = productData.image,
                    ratingCount = productData.rating?.count.toString(),
                    rating = productData.rating?.rate?.toFloat()
                )
            }
        }
    }
}
