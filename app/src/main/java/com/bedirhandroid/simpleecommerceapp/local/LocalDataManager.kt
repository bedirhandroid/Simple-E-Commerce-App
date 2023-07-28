package com.bedirhandroid.simpleecommerceapp.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.product.ProductResponseUi
import com.bedirhandroid.simpleecommerceapp.network.models.uiresponses.users.UsersResponseUi
import com.bedirhandroid.simpleecommerceapp.util.Constant.KEY_LOCAL_LIST
import com.bedirhandroid.simpleecommerceapp.util.Constant.KEY_USER_LOCAL_DATA
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

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

    var localListData: ArrayList<ProductResponseUi>?
        set(value) {
            val json = Gson().toJson(value)
            pref.edit().putString(KEY_LOCAL_LIST, json).apply()
        }
        get() {
            val json = pref.getString(KEY_LOCAL_LIST, null)
            val type: Type = object : TypeToken<ArrayList<ProductResponseUi>?>() {}.type
            return Gson().fromJson(json, type)
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