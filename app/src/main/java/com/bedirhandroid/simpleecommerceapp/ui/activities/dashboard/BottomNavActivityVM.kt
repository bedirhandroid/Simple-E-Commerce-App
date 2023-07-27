package com.bedirhandroid.simpleecommerceapp.ui.activities.dashboard

import com.bedirhandroid.simpleecommerceapp.base.BaseViewModel
import com.bedirhandroid.simpleecommerceapp.network.usecases.product.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavActivityVM @Inject constructor(
    private val productUseCase: ProductUseCase
) : BaseViewModel() {

}