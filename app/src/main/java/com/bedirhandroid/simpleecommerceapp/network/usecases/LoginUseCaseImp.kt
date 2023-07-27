package com.bedirhandroid.simpleecommerceapp.network.usecases

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.dataresponses.LoginResponse
import com.bedirhandroid.simpleecommerceapp.network.requests.LoginRequest
import com.bedirhandroid.simpleecommerceapp.network.uiresponses.LoginUiResponse
import javax.inject.Inject

class LoginUseCaseImp @Inject constructor(private val repo: Repo) : LoginUseCase {
    override suspend fun login(body: LoginRequest): LoginUiResponse {
        var loginDataResponse: LoginResponse? = null
        repo.login(body).collect {
            loginDataResponse = it
        }
        return LoginUiResponse(loginDataResponse?.token)
    }
}