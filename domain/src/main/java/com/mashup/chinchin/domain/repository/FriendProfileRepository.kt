package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.FriendProfile

interface FriendProfileRepository {
    suspend fun getFriendProfile(friendId: Long): FriendProfile
}