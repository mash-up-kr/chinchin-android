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

    override fun getKakaoAccessToken(): String {
        return localLoginDataSource.getKakaoAccessToken()
    }

    /* TODO: 과연 refreshToken을 저장하는게 의미가 있는 행위일지 다시 생각해보자.
    *   kakaoSDK가 토큰을 관리해주고 있다. */
    override fun saveKakaoRefreshToken(refreshToken: String) {
        localLoginDataSource.saveKakaoRefreshToken(refreshToken)
    }

    override fun setKakaoFriendsPermission(isAgree: Boolean) {
        localLoginDataSource.setKakaoFriendsPermission(isAgree)
    }

    override fun getKakaoFriendsPermission(): Boolean {
        return localLoginDataSource.getKakaoFriendsPermission()
    }

    override suspend fun login(accessToken: String): String? {
        return remoteLoginDataSource.login(LoginRequestBody(accessToken)).jwt
    }

    override fun setIsFirstEnter(isFirstEnter: Boolean) {
        localLoginDataSource.setIsFirstEnter(isFirstEnter)
    }

    override fun getIsFirstEnter(): Boolean {
        return localLoginDataSource.getIsFirstEnter()
    }
}
