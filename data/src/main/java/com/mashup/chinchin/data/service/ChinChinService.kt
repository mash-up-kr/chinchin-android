package com.mashup.chinchin.data.service

import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.LoginRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.LoginResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChinChinService {
    @POST("/kakao-login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    @POST("groups/group")
    suspend fun createNewGroup(
        @Header("Authorization") jwt: String,
        @Body createNewGroupRequestBody: CreateNewGroupRequestBody,
    )
}
