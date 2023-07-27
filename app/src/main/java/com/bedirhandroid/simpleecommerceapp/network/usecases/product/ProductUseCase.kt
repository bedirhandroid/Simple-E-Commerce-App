package com.bedirhandroid.simpleecommerceapp.network.usecases.product

import androidx.paging.PagingData
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    suspend fun getProducts(): Flow<PagingData<ProductResponseUi>>
}