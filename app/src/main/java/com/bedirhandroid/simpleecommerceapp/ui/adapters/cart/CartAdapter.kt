package com.bedirhandroid.simpleecommerceapp.ui.adapters.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.simpleecommerceapp.databinding.CartRowBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi

class CartAdapter(
    private val itemList: ArrayList<ProductResponseUi>,
    private val clickItem: (ProductResponseUi) -> Unit,
) : RecyclerView.Adapter<CartAdapter.CartListVH>() {

    class CartListVH(val binding: CartRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListVH {
        val binding = CartRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartListVH(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CartListVH, position: Int) {
        val item = itemList[position]
        holder.binding.apply {
            ivProduct.setOnClickListener {
                clickItem.invoke(item)
            }
        }
    }
}