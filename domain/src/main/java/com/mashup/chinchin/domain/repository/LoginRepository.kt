package com.mashup.chinchin.domain.repository

interface LoginRepository {
    fun setJwt(jwt: String)
    fun getJwt(): String
    fun saveKakaoAccessToken(accessToken: String)
    fun getKakaoAccessToken(): String
    fun saveKakaoRefreshToken(refreshToken: String)
    fun setKakaoFriendsPermission(isAgree: Boolean)
    fun getKakaoFriendsPermission(): Boolean
    suspend fun login(accessToken: String): String?
    fun setIsFirstEnter(isFirstEnter: Boolean)
    fun getIsFirstEnter(): Boolean
}
