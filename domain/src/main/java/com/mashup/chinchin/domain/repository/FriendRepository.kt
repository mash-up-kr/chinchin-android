package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.domain.usecase.AddFriendParams
import com.mashup.chinchin.domain.usecase.UpdateFriendParams

interface FriendRepository {
    suspend fun getFriendProfile(friendId: Long): FriendProfile
    suspend fun getFriends(): List<Friend>
    suspend fun addFriend(friend: AddFriendParams): Long
    suspend fun updateFriend(friend: UpdateFriendParams): Long
}