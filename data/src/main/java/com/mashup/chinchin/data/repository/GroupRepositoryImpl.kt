package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteGroupDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.domain.model.Groups
import com.mashup.chinchin.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val remoteGroupDataSource: RemoteGroupDataSource,
): GroupRepository {
    override suspend fun createNewGroup(jwt: String, groupName: String): Boolean {
        return remoteGroupDataSource.createNewGroup(
            jwt = jwt,
            createNewGroupRequestBody = CreateNewGroupRequestBody(groupName),
        ).isSuccess ?: false
    }

    override suspend fun getGroups(jwt: String): Groups {
        return Groups(remoteGroupDataSource.getGroups(jwt).map { it.toDomainModel() })
    }
}
