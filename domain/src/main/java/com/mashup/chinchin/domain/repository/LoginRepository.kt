package com.mashup.chinchin.domain.repository

interface LoginRepository {
    fun setJwt(jwt: String)
    fun getJwt(): String
    suspend fun login(accessToken: String): String?
}
