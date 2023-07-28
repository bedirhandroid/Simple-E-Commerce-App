package com.bedirhandroid.simpleecommerceapp.ui.fragments.dashboard

import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.base.BaseFragment
import com.bedirhandroid.simpleecommerceapp.databinding.FragmentDashboardBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.ui.adapters.product.ProductListAdapter
import com.bedirhandroid.simpleecommerceapp.util.Constant
import com.bedirhandroid.simpleecommerceapp.util.addToCart
import com.bedirhandroid.simpleecommerceapp.util.navigateWithBundleTo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    private lateinit var productListAdapter: ProductListAdapter
    override fun initView() {
        viewBindingScope {
            productListAdapter = ProductListAdapter(
                ::onClickAddToCart,
                ::onClickAdapterItem
            )
            rvProduct.adapter = productListAdapter
        }
    }

    override fun initListeners() {

    }

    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProducts().collectLatest {
                productListAdapter.submitData(it)
            }
        }
    }

    private fun onClickAddToCart(data: ProductResponseUi) {
        addToCart(requireContext(), data)
    }

    private fun onClickAdapterItem(data: ProductResponseUi) {
        navigateWithBundleTo(
            R.id.action_navigation_dashboard_to_detailFragment,
            bundle = bundleOf(Constant.KEY_PRODUCT_ID to data.id)
        )
    }
}