package com.bedirhandroid.simpleecommerceapp.network.usecases.product.sinlgeproduct

import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import kotlinx.coroutines.flow.Flow

interface SingleProductUseCase {
    suspend fun getSingleProduct(id: String): Flow<ProductResponseUi?>
}