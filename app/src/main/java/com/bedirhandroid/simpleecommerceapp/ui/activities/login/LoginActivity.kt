package com.bedirhandroid.simpleecommerceapp.ui.activities.login

import androidx.lifecycle.Observer
import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.base.BaseActivity
import com.bedirhandroid.simpleecommerceapp.databinding.ActivityMainBinding
import com.bedirhandroid.simpleecommerceapp.local.LocalDataManager
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi
import com.bedirhandroid.simpleecommerceapp.ui.activities.dashboard.BottomNavActivity
import com.bedirhandroid.simpleecommerceapp.ui.adapters.users.UsersAdapter
import com.bedirhandroid.simpleecommerceapp.util.navigateTo
import com.bedirhandroid.simpleecommerceapp.util.observerNotNull
import com.bedirhandroid.simpleecommerceapp.util.showAlert

class LoginActivity : BaseActivity<ActivityMainBinding, LoginActivityVM>() {

    private val usersObserver: Observer<List<UsersResponseUi>> = observerNotNull {
        binding.rvUsers.adapter = UsersAdapter(it, ::clickAdapterItem)
    }

    private val isEditedObserver: Observer<Boolean> = observerNotNull {
        viewModel.usersListLiveData.value?.find { _usersData ->
            binding.etUsename.text.toString() == _usersData.username
                    && binding.etPassword.text.toString() == _usersData.password
        }?.let {
            showAlert(
                title = getString(R.string.success_login),
                msg = getString(R.string.msg_nav_dashboard)
            ) {
                LocalDataManager.getInstance().apply {
                    loginUserData = it
                }
                navigateDashboard()
            }
        } ?: kotlin.run {
            showAlert(
                title = getString(R.string.attention),
                msg = getString(R.string.user_not_found)
            )
        }
    }

    private fun navigateDashboard() {
        navigateTo<BottomNavActivity>(finishRequired = true)
    }

    override fun initView() {}

    override fun initListeners() {
        viewBindingScope {
            btnLogin.setOnClickListener {
                viewModel.checkEditTexts.postValue(true)
            }
        }
    }

    override fun initObservers() {
        viewModelScope {
            usersListLiveData.observe(this@LoginActivity, usersObserver)
            checkEditTexts.observe(this@LoginActivity, isEditedObserver)
        }
    }

    private fun clickAdapterItem(data: UsersResponseUi) {
        viewBindingScope {
            etUsename.setText(data.username)
            etPassword.setText(data.password)
            viewModel.checkEditTexts.postValue(true)
        }
    }
}