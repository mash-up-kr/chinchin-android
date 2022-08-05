package com.mashup.domain.repository

interface LoginRepository {
    fun setJwt(jwt: String)
    fun getJwt(): String
}
