package com.bedirhandroid.simpleecommerceapp.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bedirhandroid.simpleecommerceapp.network.ApiService
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.login.LoginResponseData
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.product.ProductResponseDataItem
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.users.UsersResponseDataList
import com.bedirhandroid.simpleecommerceapp.network.models.requests.LoginRequest
import com.bedirhandroid.simpleecommerceapp.paging.PagingListAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo @Inject constructor(private val apiService: ApiService) {
    suspend fun login(body: LoginRequest): Flow<LoginResponseData?> {
        return flow {
            emit(apiService.login(body))
        }
    }

    fun getProducts() :Flow<PagingData<ProductResponseDataItem>> {
        return Pager(
            config = PagingConfig(
                10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingListAdapter(apiService) }
        ).flow
    }

    suspend fun getUsers(): Flow<UsersResponseDataList?> {
        return flow { emit(apiService.getUsers()) }
    }

    suspend fun getSingleProduct(id: String): Flow<ProductResponseDataItem?> {
        return flow {
            emit(apiService.getSingleProduct(id))
        }
    }
}