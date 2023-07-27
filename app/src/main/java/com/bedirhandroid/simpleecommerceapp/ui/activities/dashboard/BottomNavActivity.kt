package com.bedirhandroid.simpleecommerceapp.ui.activities.dashboard

import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.base.BaseActivity
import com.bedirhandroid.simpleecommerceapp.databinding.ActivityBottomNavBinding

class BottomNavActivity : BaseActivity<ActivityBottomNavBinding, BottomNavActivityVM>() {

    override fun initView() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom_nav)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_favorites, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun initListeners() {

    }

    override fun initObservers() {
    }
}