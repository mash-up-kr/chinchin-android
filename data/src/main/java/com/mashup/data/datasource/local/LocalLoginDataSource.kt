package com.mashup.data.datasource.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalLoginDataSource @Inject constructor(
    private val preferences: SharedPreferences,
) {
    fun getJwt() = preferences.getString(JWT, "") ?: ""

    fun setJwt(jwt: String) {
        preferences.edit().putString(JWT, jwt).apply()
    }

    companion object {
        const val JWT = "JWT"
    }
}
