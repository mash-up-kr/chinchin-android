package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteGroupDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.domain.model.GroupDetail
import com.mashup.chinchin.domain.model.Groups
import com.mashup.chinchin.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val remoteGroupDataSource: RemoteGroupDataSource,
): GroupRepository {
    override suspend fun createNewGroup(groupName: String): Boolean {
        return remoteGroupDataSource.createNewGroup(
            createNewGroupRequestBody = CreateNewGroupRequestBody(groupName),
        ).isSuccess ?: false
    }

    override suspend fun getGroups(): Groups {
        return Groups(remoteGroupDataSource.getGroups().map { it.toDomainModel() })
    }

    override suspend fun getFriendsInGroup(groupId: Long): GroupDetail {
        return remoteGroupDataSource.getFriendsInGroup(groupId).toDomainModel()
    }
}
