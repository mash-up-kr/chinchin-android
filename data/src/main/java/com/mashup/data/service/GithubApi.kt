package com.mashup.data.service

import com.mashup.data.dto.remote.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}")
    suspend fun getUserInfo(@Path("username") userName: String): UserInfo
}
