package com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users

import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.users.Address
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.users.Name
import com.google.gson.annotations.SerializedName

data class UsersResponseUi(
    val address: Address? = null,
    val email: String? = null,
    val id: Int? = null,
    val name: Name? = null,
    val password: String? = null,
    val phone: String? = null,
    val username: String? = null
)
