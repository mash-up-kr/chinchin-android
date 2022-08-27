package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.model.FriendProfile

interface FriendRepository {
    suspend fun getFriendProfile(friendId: Long): FriendProfile
    suspend fun getFriends(): List<Friend>
    suspend fun addFriend(friend: Friend): Long
}