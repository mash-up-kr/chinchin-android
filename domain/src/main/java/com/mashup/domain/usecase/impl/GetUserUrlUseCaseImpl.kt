package com.mashup.domain.usecase.impl

import com.mashup.data.repository.GithubRepository
import com.mashup.domain.usecase.GetUserUrlUseCase
import javax.inject.Inject

class GetUserUrlUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : GetUserUrlUseCase {
    override suspend operator fun invoke(userName: String): String {
        return githubRepository.getUserInfo("AnKyungMoo").url?: "T_T"
    }
}
