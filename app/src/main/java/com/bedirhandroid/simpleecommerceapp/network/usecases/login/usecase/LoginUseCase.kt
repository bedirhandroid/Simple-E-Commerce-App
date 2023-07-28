package com.bedirhandroid.simpleecommerceapp.network.usecases.login.usecase

import com.bedirhandroid.simpleecommerceapp.network.models.requests.LoginRequest
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.login.LoginResponseUi


interface LoginUseCase {
    suspend fun login(body: LoginRequest): LoginResponseUi
}