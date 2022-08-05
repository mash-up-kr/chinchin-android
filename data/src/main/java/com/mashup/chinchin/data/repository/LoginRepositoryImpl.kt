package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.local.LocalLoginDataSource
import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localLoginDataSource: LocalLoginDataSource
) : LoginRepository {
    override fun setJwt(jwt: String) {
        localLoginDataSource.setJwt(jwt)
    }

    override fun getJwt() = localLoginDataSource.getJwt()
}
