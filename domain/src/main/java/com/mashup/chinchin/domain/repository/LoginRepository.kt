package com.mashup.chinchin.domain.repository

interface LoginRepository {
    fun setJwt(jwt: String)
    fun getJwt(): String
}
