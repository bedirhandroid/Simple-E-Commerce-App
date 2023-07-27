package com.bedirhandroid.simpleecommerceapp.base

import com.bedirhandroid.simpleecommerceapp.network.ApiService
import com.bedirhandroid.simpleecommerceapp.network.dataresponses.LoginResponse
import com.bedirhandroid.simpleecommerceapp.network.requests.LoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo @Inject constructor(private val apiService: ApiService) {
    suspend fun login(body: LoginRequest) : Flow<LoginResponse?> {
        return flow {
           emit(apiService.login(body))
        }
    }
}