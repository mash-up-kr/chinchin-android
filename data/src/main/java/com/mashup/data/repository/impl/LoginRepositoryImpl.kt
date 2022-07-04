package com.mashup.data.repository.impl

import com.mashup.data.datasource.local.LocalLoginDataSource
import com.mashup.data.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localLoginDataSource: LocalLoginDataSource
) : LoginRepository {
    override fun setJwt(jwt: String) {
        localLoginDataSource.setJwt(jwt)
    }

    override fun getJwt() = localLoginDataSource.getJwt()
}
