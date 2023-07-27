package com.bedirhandroid.simpleecommerceapp.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi
import com.bedirhandroid.simpleecommerceapp.util.Constant.KEY_USER_LOCAL_DATA
import com.google.gson.Gson

class LocalDataManager(context: Context) {

    var loginUserData: UsersResponseUi?
        get() {
            val userData = pref.getString(KEY_USER_LOCAL_DATA, null)
            return userData?.let {
                Gson().fromJson(userData, UsersResponseUi::class.java)

            }
        }
        set(value) {
            val userData = Gson().toJson(value)
            if (userData != "") {
                pref.edit { putString(KEY_USER_LOCAL_DATA, userData) }
            }
        }


    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    fun clearData() = pref.edit().clear().apply()

    companion object {
        private var instance: LocalDataManager? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): LocalDataManager {
            return instance!!
        }

        @JvmStatic
        fun init(context: Context) {
            if (instance == null) {
                instance = LocalDataManager(context)
            }
        }
    }
}