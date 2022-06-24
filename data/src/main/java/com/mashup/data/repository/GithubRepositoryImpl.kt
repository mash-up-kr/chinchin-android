package com.mashup.data.repository

import com.mashup.data.dto.remote.UserInfo
import com.mashup.data.service.GithubApi
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val githubApi: GithubApi) {
    suspend fun getUserInfo(userName: String): UserInfo? {
        return githubApi.getUserInfo(userName)
    }
}