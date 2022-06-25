package com.mashup.data.repository.impl

import com.mashup.data.dto.remote.responsebody.UserInfo
import com.mashup.data.repository.GithubRepository
import com.mashup.data.service.GithubApi
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
) : GithubRepository {
    override suspend fun getUserInfo(userName: String): UserInfo {
        return githubApi.getUserInfo(userName)
    }
}
