package com.bedirhandroid.simpleecommerceapp.ui.adapters.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bedirhandroid.simpleecommerceapp.databinding.UsersRowBinding
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi

class UsersAdapter(
    private val itemList: List<UsersResponseUi>,
    private val clickItem: (UsersResponseUi) -> Unit,
) : RecyclerView.Adapter<UsersAdapter.UsersListVH>() {

    class UsersListVH(val binding: UsersRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListVH {
        val binding = UsersRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersListVH(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: UsersListVH, position: Int) {
        holder.binding.apply {
            itemList[position].apply {
                tvUserName.text = username
                tvPassword.text = password
                cvItem.setOnClickListener { clickItem.invoke(this) }
            }
        }
    }
}