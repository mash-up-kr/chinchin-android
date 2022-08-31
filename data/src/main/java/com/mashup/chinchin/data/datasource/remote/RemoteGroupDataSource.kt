package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.CreateNewGroupResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetFriendsInGroupResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetGroupsResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteGroupDataSource @Inject constructor(
    private val chinChinService: ChinChinService,
) {
    suspend fun createNewGroup(
        createNewGroupRequestBody: CreateNewGroupRequestBody,
    ): CreateNewGroupResponseBody {
        return chinChinService.createNewGroup(
            createNewGroupRequestBody = createNewGroupRequestBody,
        )
    }

    suspend fun getGroups(): List<GetGroupsResponseBody> = chinChinService.getGroups()

    suspend fun getFriendsInGroup(groupId: Long): GetFriendsInGroupResponseBody =
        chinChinService.getFriendsInGroup(groupId)

}
