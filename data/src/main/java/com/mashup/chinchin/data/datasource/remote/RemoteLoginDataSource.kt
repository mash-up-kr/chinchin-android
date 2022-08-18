package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.requestbody.LoginRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.LoginResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteLoginDataSource @Inject constructor(
    private val chinChinService: ChinChinService,
) {
    suspend fun login(loginRequestBody: LoginRequestBody): LoginResponseBody {
        return chinChinService.login(loginRequestBody)
    }
}
