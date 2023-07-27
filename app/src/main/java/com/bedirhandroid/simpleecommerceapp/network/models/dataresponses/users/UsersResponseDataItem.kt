package com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.users

import com.google.gson.annotations.SerializedName

data class UsersResponseDataItem(
    @SerializedName("__v")
    val unknown: Int? = null,
    val address: Address? = null,
    val email: String? = null,
    val id: Int? = null,
    val name: Name? = null,
    val password: String? = null,
    val phone: String? = null,
    val username: String? = null
)