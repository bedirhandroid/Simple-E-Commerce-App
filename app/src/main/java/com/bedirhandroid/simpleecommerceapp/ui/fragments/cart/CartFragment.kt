package com.bedirhandroid.simpleecommerceapp.ui.fragments.cart

import androidx.core.os.bundleOf
import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.base.BaseFragment
import com.bedirhandroid.simpleecommerceapp.databinding.FragmentCartBinding
import com.bedirhandroid.simpleecommerceapp.local.LocalDataManager
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.ui.adapters.cart.CartAdapter
import com.bedirhandroid.simpleecommerceapp.util.Constant
import com.bedirhandroid.simpleecommerceapp.util.gone
import com.bedirhandroid.simpleecommerceapp.util.navigateTo
import com.bedirhandroid.simpleecommerceapp.util.navigateWithBundleTo
import com.bedirhandroid.simpleecommerceapp.util.showAlert
import com.bedirhandroid.simpleecommerceapp.util.visible

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>() {
    var totalPrice: Double = 0.0

    override fun initView() {
        initDynamicView()
    }

    override fun initListeners() {
        viewBindingScope {
            btnBuy.setOnClickListener {
                requireContext().showAlert(
                    title = getString(R.string.success),
                    msg = getString(R.string.cart_apply_msg, totalPrice)
                ) {
                    navigateTo(R.id.action_navigation_cart_to_navigation_dashboard)
                    LocalDataManager.getInstance().localListData = null
                }
            }
            tvEmptyList.setOnClickListener {
                navigateTo(R.id.action_navigation_cart_to_navigation_dashboard)
            }
        }
    }

    override fun initObservers() {}
    private fun clickAdapter(data: ProductResponseUi) {
        navigateWithBundleTo(
            R.id.action_navigation_cart_to_detailFragment,
            bundle = bundleOf(Constant.KEY_PRODUCT_ID to data.id)
        )
    }

    private fun initDynamicView() {
        viewBindingScope {
            LocalDataManager.getInstance().localListData?.let {
                tvEmptyList.gone()
                buyContainer.visible()
                rvCart.adapter = LocalDataManager.getInstance().localListData?.let {
                    CartAdapter(
                        it,
                        ::clickAdapter
                    )
                }
                LocalDataManager.getInstance().localListData?.map {
                    it.price?.toDouble()
                }.let {
                    it?.forEach { _price ->
                        if (_price != null) {
                            totalPrice += _price
                            tvTotalPrice.text = getString(R.string.total_price, totalPrice)
                        }
                    }
                }
            } ?: kotlin.run {
                tvEmptyList.visible()
                buyContainer.gone()
            }
        }
    }
}