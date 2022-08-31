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

    fun saveKakaoAccessToken(accessToken: String) {
        preferences.edit().putString(KAKAO_ACCESS_TOKEN, accessToken).apply()
    }

    fun getKakaoAccessToken(): String {
        return preferences.getString(KAKAO_ACCESS_TOKEN, "") ?: ""
    }

    fun saveKakaoRefreshToken(refreshToken: String) {
        preferences.edit().putString(KAKAO_REFRESH_TOKEN, refreshToken).apply()
    }

    companion object {
        const val JWT = "JWT"
        const val KAKAO_ACCESS_TOKEN = "KAKAO_ACCESS_TOKEN"
        const val KAKAO_REFRESH_TOKEN = "KAKAO_REFRESH_TOKEN"
        const val LOGIN_PREF = "LOGIN_PREF"
    }
}
