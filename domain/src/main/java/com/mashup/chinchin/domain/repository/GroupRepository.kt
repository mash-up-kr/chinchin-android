package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.Groups

interface GroupRepository {
    suspend fun createNewGroup(jwt: String, groupName: String): Boolean
    suspend fun getGroups(jwt: String): Groups
}
