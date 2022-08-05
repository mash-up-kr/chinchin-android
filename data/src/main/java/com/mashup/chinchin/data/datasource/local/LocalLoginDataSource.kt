package com.mashup.chinchin.data.datasource.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LocalLoginDataSource @Inject constructor(
    application: Application
) {
    private val preferences: SharedPreferences = application.getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)

    fun getJwt() = preferences.getString(JWT, "") ?: ""

    fun setJwt(jwt: String) {
        preferences.edit().putString(JWT, jwt).apply()
    }

    companion object {
        const val JWT = "JWT"
        const val LOGIN_PREF = "LOGIN_PREF"
    }
}
