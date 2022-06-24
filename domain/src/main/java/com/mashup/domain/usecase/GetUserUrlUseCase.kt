package com.mashup.domain.usecase

interface GetUserUrlUseCase {
    suspend operator fun invoke(userName: String): String
}
