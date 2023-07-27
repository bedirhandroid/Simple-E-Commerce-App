package com.bedirhandroid.simpleecommerceapp.network

import com.bedirhandroid.simpleecommerceapp.network.dataresponses.LoginResponse
import com.bedirhandroid.simpleecommerceapp.network.requests.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
   suspend fun login(@Body loginRequest: LoginRequest): LoginResponse?
}