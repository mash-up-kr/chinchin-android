package com.mashup.data.repository

import com.mashup.data.dto.remote.UserInfo
import com.mashup.data.service.GithubApi
import javax.inject.Inject

interface GithubRepository {
    suspend fun getUserInfo(userName: String): UserInfo
}
