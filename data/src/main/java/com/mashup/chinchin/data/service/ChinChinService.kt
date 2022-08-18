package com.mashup.chinchin.data.service

import com.mashup.chinchin.data.dto.remote.requestbody.LoginRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.LoginResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ChinChinService {
    @POST("/kakao-login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): LoginResponseBody
}
