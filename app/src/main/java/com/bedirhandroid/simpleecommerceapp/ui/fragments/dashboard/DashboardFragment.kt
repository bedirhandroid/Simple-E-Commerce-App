package com.bedirhandroid.simpleecommerceapp.ui.fragments.dashboard

import androidx.lifecycle.lifecycleScope
import com.bedirhandroid.simpleecommerceapp.base.BaseFragment
import com.bedirhandroid.simpleecommerceapp.databinding.FragmentDashboardBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.ui.adapters.ProductListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    private lateinit var productListAdapter: ProductListAdapter
    override fun initView() {
        viewBindingScope {
            productListAdapter = ProductListAdapter(::onClickAdapter)
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

    private fun onClickAdapter(data: ProductResponseUi) {

    }

}