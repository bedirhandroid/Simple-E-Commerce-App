package com.bedirhandroid.simpleecommerceapp.ui.activities.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.bedirhandroid.simpleecommerceapp.base.BaseActivity
import com.bedirhandroid.simpleecommerceapp.databinding.ActivitySplashBinding
import com.bedirhandroid.simpleecommerceapp.local.LocalDataManager
import com.bedirhandroid.simpleecommerceapp.ui.activities.dashboard.BottomNavActivity
import com.bedirhandroid.simpleecommerceapp.ui.activities.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun initView() {
        binding.lvAnimation.playAnimation()
        dynamicNavigate()
    }

    override fun initListeners() {

    }

    override fun initObservers() {

    }

    private fun dynamicNavigate() {
        Handler(Looper.getMainLooper()).postDelayed({
            when (LocalDataManager.getInstance().loginUserData != null) {
                true -> BottomNavActivity::class.java
                else -> LoginActivity::class.java
            }.let {
                Intent(this, it).also { _intent ->
                    startActivity(_intent)
                    finish()
                }
            }
        }, 2000)
    }

}