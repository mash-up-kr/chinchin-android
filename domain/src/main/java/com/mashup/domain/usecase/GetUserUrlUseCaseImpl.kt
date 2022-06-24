package com.mashup.domain.usecase

import com.mashup.data.repository.GithubRepository
import javax.inject.Inject

class GetUserUrlUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : GetUserUrlUseCase {
    override suspend operator fun invoke(userName: String): String {
        return githubRepository.getUserInfo("AnKyungMoo").url?: "T_T"
    }
}
