package com.mashup.data.repository

interface LoginRepository {
    fun setJwt(jwt: String)
    fun getJwt(): String
}
