package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.CreateNewGroupResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteGroupDataSource @Inject constructor(
    private val chinChinService: ChinChinService,
) {
    suspend fun createNewGroup(
        jwt: String,
        createNewGroupRequestBody: CreateNewGroupRequestBody,
    ): CreateNewGroupResponseBody {
        return chinChinService.createNewGroup(
            jwt = jwt,
            createNewGroupRequestBody = createNewGroupRequestBody,
        )
    }
}
