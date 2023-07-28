package com.bedirhandroid.simpleecommerceapp.ui.fragments.detail

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.base.BaseFragment
import com.bedirhandroid.simpleecommerceapp.databinding.FragmentDetailBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.util.Constant
import com.bedirhandroid.simpleecommerceapp.util.addToCart
import com.bedirhandroid.simpleecommerceapp.util.loadImage
import com.bedirhandroid.simpleecommerceapp.util.observerNotNull
import kotlinx.coroutines.launch

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>() {

    private var productId: String? = null

    private val productDataObserver: Observer<ProductResponseUi> = observerNotNull {
        initUi(it)
    }

    override fun initView() {
        getArgs()
    }

    override fun initListeners() {
        viewBindingScope {
            btnAddToCart.setOnClickListener {
                viewModel.productLiveData.value?.let { _data ->
                    addToCart(requireContext(), _data)
                }
            }
        }
    }

    override fun initObservers() {
        viewModelScope {
            productLiveData.observe(viewLifecycleOwner, productDataObserver)
        }
    }

    private fun getArgs() {
        productId = arguments?.getString(Constant.KEY_PRODUCT_ID)
        productId?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getSingleProduct(it)
            }
        }
    }

    private fun initUi(data: ProductResponseUi) {
        viewBindingScope {
            tvTitleProduct.text = data.title
            tvDescProduct.text = data.description
            data.image?.let(ivProduct::loadImage)
            tvRateCount.text = getString(R.string.rate, data.ratingCount)
            rbRate.rating = data.rating!!
        }
    }

}