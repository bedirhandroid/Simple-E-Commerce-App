package com.bedirhandroid.simpleecommerceapp.ui.fragments.dashboard

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bedirhandroid.simpleecommerceapp.base.BaseViewModel
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.network.usecases.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
) : BaseViewModel() {

    suspend fun getProducts(): Flow<PagingData<ProductResponseUi>> {
        return productUseCase.getProducts().cachedIn(viewModelScope)
    }
}