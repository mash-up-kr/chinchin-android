package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(accessToken: String): Boolean {
        val jwt: String = loginRepository.login(accessToken) ?: return false

        loginRepository.setJwt(jwt)
        return true
    }
}
