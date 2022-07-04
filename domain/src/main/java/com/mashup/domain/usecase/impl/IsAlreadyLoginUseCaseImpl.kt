package com.mashup.domain.usecase.impl

import com.mashup.data.repository.LoginRepository
import com.mashup.domain.usecase.IsAlreadyLoginUseCase
import javax.inject.Inject

class IsAlreadyLoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
): IsAlreadyLoginUseCase {
    override suspend fun invoke(): Boolean = loginRepository.getJwt().isBlank().not()
}
