package com.bedirhandroid.simpleecommerceapp.network.usecases.login.usecase

import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.login.LoginResponseData
import com.bedirhandroid.simpleecommerceapp.network.models.requests.LoginRequest
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.login.LoginResponseUi
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val repo: Repo) : LoginUseCase {
    override suspend fun login(body: LoginRequest): LoginResponseUi {
        var loginDataResponse: LoginResponseData? = null
        repo.login(body).collect {
            loginDataResponse = it
        }
        return LoginResponseUi(
            token = loginDataResponse?.token
        )
    }
}