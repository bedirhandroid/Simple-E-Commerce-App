package com.bedirhandroid.simpleecommerceapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.simpleecommerceapp.databinding.ListRowBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.util.loadImage

class ProductListAdapter(private val clickItem: (ProductResponseUi) -> Unit): PagingDataAdapter<ProductResponseUi, ProductListAdapter.ListAdapterVH>(LIST_COMPARATOR) {

    class ListAdapterVH(val binding: ListRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductResponseUi) {
            binding.apply {
                tvProductTitle.text = data.title
                tvPrice.text = data.price
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
            getItem(position)?.let { _data ->
                bind(_data)
                binding.btnAddToCart.setOnClickListener { clickItem.invoke(_data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterVH {
        val binding = ListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAdapterVH(binding)
    }
}