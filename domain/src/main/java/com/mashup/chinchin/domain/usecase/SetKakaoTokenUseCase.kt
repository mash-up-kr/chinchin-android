package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class SetKakaoTokenUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(accessToken: String, refreshToken: String) {
        loginRepository.saveKakaoAccessToken(accessToken = accessToken)
        loginRepository.saveKakaoRefreshToken(refreshToken = refreshToken)
    }
}
