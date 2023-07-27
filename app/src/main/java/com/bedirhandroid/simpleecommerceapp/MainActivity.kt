package com.bedirhandroid.simpleecommerceapp

import com.bedirhandroid.simpleecommerceapp.base.BaseActivity
import com.bedirhandroid.simpleecommerceapp.databinding.ActivityMainBinding
import com.bedirhandroid.simpleecommerceapp.network.requests.LoginRequest

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(){
    override fun initView() {

    }

    override fun initListeners() {
        viewBindingScope {
            tvBtn.setOnClickListener {
                viewModel.login(
                    LoginRequest(
                        "mor_2314",
                        "83r5^_"
                    )
                )
            }
        }
    }

    override fun initObservers() {

    }
}