package com.bedirhandroid.simpleecommerceapp.ui.adapters.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.databinding.ListRowBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.util.loadImage

class ProductListAdapter(
    private val clickAddToCart: (ProductResponseUi) -> Unit,
    private val clickItem: (ProductResponseUi) -> Unit
) :
    PagingDataAdapter<ProductResponseUi, ProductListAdapter.ListAdapterVH>(LIST_COMPARATOR) {

    class ListAdapterVH(val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductResponseUi) {
            binding.apply {
                tvProductTitle.text = data.title
                tvPrice.text = this.ivProduct.context.getString(R.string.price, data.price)
                data.image?.let(ivProduct::loadImage)
            }
        }
    }

    companion object {
        private val LIST_COMPARATOR =
            object : DiffUtil.ItemCallback<ProductResponseUi>() {
                override fun areItemsTheSame(
                    oldItem: ProductResponseUi,
                    newItem: ProductResponseUi
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: ProductResponseUi,
                    newItem: ProductResponseUi
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onBindViewHolder(holder: ListAdapterVH, position: Int) {
        holder.apply {
            getItem(position)?.let { data ->
                bind(data)
                binding.apply {
                    btnAddToCart.setOnClickListener { clickAddToCart.invoke(data) }
                    root.setOnClickListener { clickItem.invoke(data) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterVH {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAdapterVH(binding)
    }
}