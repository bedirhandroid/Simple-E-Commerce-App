package com.bedirhandroid.simpleecommerceapp.network.usecases

import com.bedirhandroid.simpleecommerceapp.network.requests.LoginRequest
import com.bedirhandroid.simpleecommerceapp.network.uiresponses.LoginUiResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


interface LoginUseCase {
    suspend fun login(body: LoginRequest) : LoginUiResponse
}