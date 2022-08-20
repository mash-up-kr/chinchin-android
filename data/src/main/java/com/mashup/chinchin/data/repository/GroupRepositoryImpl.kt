package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteGroupDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val remoteGroupDataSource: RemoteGroupDataSource,
) {
    suspend fun createNewGroup(jwt: String, groupName: String) {
        remoteGroupDataSource.createNewGroup(
            jwt = jwt,
            createNewGroupRequestBody = CreateNewGroupRequestBody(groupName),
        )
    }
}