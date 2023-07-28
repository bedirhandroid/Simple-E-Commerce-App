package com.bedirhandroid.simpleecommerceapp.network

import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.login.LoginResponseData
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.product.ProductDataList
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.product.ProductResponseDataItem
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.users.UsersResponseDataList
import com.bedirhandroid.simpleecommerceapp.network.models.requests.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponseData?

    @GET("products")
    suspend fun getUsers(
        @Query("limit") limit: Int = 0
    ): ProductDataList

    @GET("products/{id}")
    suspend fun getSingleProduct(
        @Path("id") id: String
    ): ProductResponseDataItem

    @GET("users")
    suspend fun getUsers(): UsersResponseDataList?
}