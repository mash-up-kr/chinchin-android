package com.mashup.chinchin.data.datasource.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LocalCommonDataSource @Inject constructor(
    application: Application
) {
    private val preferences: SharedPreferences = application.getSharedPreferences(COMMON_PREF, Context.MODE_PRIVATE)

    fun getIsFirstEnter(): Boolean {
        return preferences.getBoolean(IS_FIRST_ENTER, true)
    }

    fun setIsFirstEnter(isFirstEnter: Boolean) {
        preferences.edit().putBoolean(IS_FIRST_ENTER, isFirstEnter).apply()
    }

    companion object {
        const val COMMON_PREF = "COMMON_PREF"

        const val IS_FIRST_ENTER = "IS_FIRST_ENTER"
    }
}
