package com.mashup.domain.usecase

interface IsAlreadyLoginUseCase {
    suspend operator fun invoke(): Boolean
}
