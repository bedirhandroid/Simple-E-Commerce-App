package com.bedirhandroid.simpleecommerceapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.EOFException
import java.net.ProtocolException
import java.util.concurrent.TimeoutException

abstract class BaseViewModel : ViewModel() {
    val errorLiveData: MutableLiveData<ErrorMessages> = MutableLiveData()
    val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    //inline coroutines scope
    protected inline fun sendRequest(
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            showProgress.postValue(true)
            try {
                block()
            } catch (exception: Exception) {
                when (exception) {
                    is TimeoutException -> errorLiveData.postValue(ErrorMessages.TIME_OUT)
                    is ProtocolException -> errorLiveData.postValue(ErrorMessages.TRY_AGAIN)
                    is EOFException -> errorLiveData.postValue(ErrorMessages.ERROR_EOFE)
                    else -> {
                        errorLiveData.postValue(ErrorMessages.UNKNOWN_ERROR)
                    }
                }
            } finally {
                showProgress.postValue(false)
            }
        }
    }
}