package com.bedirhandroid.simpleecommerceapp.ui.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.simpleecommerceapp.base.BaseViewModel
import com.bedirhandroid.simpleecommerceapp.base.ErrorMessages
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.network.usecases.product.sinlgeproduct.SingleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val singleProductUseCase: SingleProductUseCase
) : BaseViewModel() {

    private val mutableProductData = MutableLiveData<ProductResponseUi>()
    val productLiveData: LiveData<ProductResponseUi> get() = mutableProductData

    suspend fun getSingleProduct(id: String) {
        sendRequest {
            singleProductUseCase.getSingleProduct(id).collectLatest {
                it?.let(mutableProductData::postValue) ?: kotlin.run {
                    errorLiveData.postValue(ErrorMessages.ERROR)
                }
            }
        }
    }
}