package com.bedirhandroid.simpleecommerceapp.network.usecases.product.sinlgeproduct

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SingleProductUseCaseImpl @Inject constructor(private val repo: Repo) : SingleProductUseCase {
    override suspend fun getSingleProduct(id: String): Flow<ProductResponseUi?> {
        return repo.getSingleProduct(id).map {
            ProductResponseUi(
                id = it?.id.toString(),
                title = it?.title,
                price = it?.price,
                description = it?.description,
                image = it?.image,
                ratingCount = it?.rating?.count.toString(),
                rating = it?.rating?.rate?.toFloat()

            )
        }
    }

}