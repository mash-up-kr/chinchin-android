package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class IsAlreadyLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    fun invoke(): Boolean = loginRepository.getJwt().isBlank().not()
}
