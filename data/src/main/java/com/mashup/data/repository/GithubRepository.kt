package com.mashup.data.repository

import com.mashup.data.dto.remote.responsebody.UserInfo

interface GithubRepository {
    suspend fun getUserInfo(userName: String): UserInfo
}
