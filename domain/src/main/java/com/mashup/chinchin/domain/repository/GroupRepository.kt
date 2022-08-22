package com.mashup.chinchin.domain.repository

interface GroupRepository {
    suspend fun createNewGroup(jwt: String, groupName: String): Boolean
}
