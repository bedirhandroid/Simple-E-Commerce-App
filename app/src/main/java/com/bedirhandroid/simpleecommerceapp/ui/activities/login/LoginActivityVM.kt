package com.bedirhandroid.simpleecommerceapp.ui.activities.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bedirhandroid.simpleecommerceapp.base.BaseViewModel
import com.bedirhandroid.simpleecommerceapp.base.ErrorMessages
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi
import com.bedirhandroid.simpleecommerceapp.network.usecases.users.usecase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class LoginActivityVM @Inject constructor(
    private val usersUseCase: UsersUseCase
) : BaseViewModel() {

    val checkEditTexts = MutableLiveData<Boolean>()

    private val mutableUsersList = MutableLiveData<List<UsersResponseUi>>()
    val usersListLiveData: LiveData<List<UsersResponseUi>> get() = mutableUsersList

    init {
        getUser()
    }

    private fun getUser() {
        sendRequest {
            usersUseCase.getUsers().collectLatest {
                it?.let(mutableUsersList::postValue) ?: kotlin.run {
                    errorLiveData.postValue(ErrorMessages.ERROR)
                }
            }
        }
    }
}