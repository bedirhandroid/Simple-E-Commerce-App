package com.bedirhandroid.simpleecommerceapp

import android.util.Log
import androidx.paging.log
import com.bedirhandroid.simpleecommerceapp.base.BaseViewModel
import com.bedirhandroid.simpleecommerceapp.base.ErrorMessages
import com.bedirhandroid.simpleecommerceapp.base.Repo
import com.bedirhandroid.simpleecommerceapp.network.requests.LoginRequest
import com.bedirhandroid.simpleecommerceapp.network.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val repo : Repo
): BaseViewModel() {
    fun login(body: LoginRequest) {
        sendRequest {
            loginUseCase.login(body).apply {
                token?.let {
                    Log.d("Bedirhan", "login: $it")
                } ?: errorLiveData.postValue(ErrorMessages.ERROR)
            }
        }
    }
}