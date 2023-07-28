package com.bedirhandroid.simpleecommerceapp.ui.fragments.profile

import com.bedirhandroid.simpleecommerceapp.R
import com.bedirhandroid.simpleecommerceapp.base.BaseFragment
import com.bedirhandroid.simpleecommerceapp.databinding.FragmentProfileBinding
import com.bedirhandroid.simpleecommerceapp.local.LocalDataManager
import com.bedirhandroid.simpleecommerceapp.ui.activities.login.LoginActivity
import com.bedirhandroid.simpleecommerceapp.util.navigateTo
import com.bedirhandroid.simpleecommerceapp.util.showAlert

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override fun initView() {
        viewBindingScope {
            LocalDataManager.getInstance().loginUserData?.let {
                tvAdress.text = it.address
                tvMail.text = it.email
                tvPhone.text = getString(R.string.phone_number, it.phone)
                tvName.text = it.fullName
            }
        }
    }

    override fun initListeners() {
        viewBindingScope {
            btnLogout.setOnClickListener {
                requireActivity().apply {
                    showAlert(
                        title = getString(R.string.success),
                        msg = getString(R.string.msg_nav_login)
                    ) {
                        LocalDataManager.getInstance().clearData()
                        navigateTo<LoginActivity>(finishRequired = true)
                    }
                }
            }
        }
    }

    override fun initObservers() {}
}