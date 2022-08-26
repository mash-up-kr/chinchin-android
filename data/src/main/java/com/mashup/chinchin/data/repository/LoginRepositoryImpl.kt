package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.local.LocalLoginDataSource
import com.mashup.chinchin.data.datasource.remote.RemoteLoginDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.LoginRequestBody
import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val localLoginDataSource: LocalLoginDataSource
) : LoginRepository {
    override fun setJwt(jwt: String) {
        localLoginDataSource.setJwt(jwt)
    }

    override fun getJwt() = localLoginDataSource.getJwt()

    override fun saveKakaoAccessToken(accessToken: String) {
        localLoginDataSource.saveKakaoAccessToken(accessToken)
    }

    override fun saveKakaoRefreshToken(refreshToken: String) {
        localLoginDataSource.saveKakaoRefreshToken(refreshToken)
    }

    override suspend fun login(accessToken: String): String? {
        return remoteLoginDataSource.login(LoginRequestBody(accessToken)).jwt
    }
}
