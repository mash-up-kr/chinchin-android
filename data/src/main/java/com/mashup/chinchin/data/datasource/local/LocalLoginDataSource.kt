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

    fun setKakaoFriendsPermission(isAgree: Boolean) {
        preferences.edit().putBoolean(KAKAO_FRIENDS_PERMISSION, isAgree).apply()
    }

    fun getKakaoFriendsPermission(): Boolean {
        return preferences.getBoolean(KAKAO_FRIENDS_PERMISSION, false)
    }

    fun getIsFirstEnter(): Boolean {
        return preferences.getBoolean(IS_FIRST_ENTER, true)
    }

    fun setIsFirstEnter(isFirstEnter: Boolean) {
        preferences.edit().putBoolean(IS_FIRST_ENTER, isFirstEnter).apply()
    }

    companion object {
        const val LOGIN_PREF = "LOGIN_PREF"

        const val JWT = "JWT"
        const val KAKAO_ACCESS_TOKEN = "KAKAO_ACCESS_TOKEN"
        const val KAKAO_REFRESH_TOKEN = "KAKAO_REFRESH_TOKEN"
        const val KAKAO_FRIENDS_PERMISSION = "KAKAO_FRIENDS_PERMISSION"

        const val IS_FIRST_ENTER = "IS_FIRST_ENTER"
    }
}
