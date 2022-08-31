package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.GroupDetail
import com.mashup.chinchin.domain.model.Groups

interface GroupRepository {
    suspend fun createNewGroup(groupName: String): Boolean
    suspend fun getGroups(): Groups
    suspend fun getFriendsInGroup(groupId: Long): GroupDetail
}
