package com.mashup.chinchin.domain.repository

interface LoginRepository {
    fun setJwt(jwt: String)
    fun getJwt(): String
    fun saveKakaoAccessToken(accessToken: String)
    fun saveKakaoRefreshToken(refreshToken: String)
    suspend fun login(accessToken: String): String?
}
