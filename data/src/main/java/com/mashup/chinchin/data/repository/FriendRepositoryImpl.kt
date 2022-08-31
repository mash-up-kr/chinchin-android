package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteFriendDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.AddFriendRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.UpdateFriendRequestBody
import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.domain.repository.FriendRepository
import com.mashup.chinchin.domain.usecase.AddFriendParams
import com.mashup.chinchin.domain.usecase.UpdateFriendParams
import javax.inject.Inject

class FriendRepositoryImpl @Inject constructor(
    private val remoteFriendDataSource: RemoteFriendDataSource
) : FriendRepository {
    override suspend fun getFriendProfile(friendId: Long): FriendProfile {
        return remoteFriendDataSource.getFriendProfile(friendId).toDomainModel()
    }

    override suspend fun getFriends(): List<Friend> {
        return remoteFriendDataSource.getFriends().friendInfo.map { it.toDomainModel() }
    }

    override suspend fun addFriend(friend: AddFriendParams): Long {
        val param = with(friend) {
            AddFriendRequestBody(
                name = name,
                dateOfBirth = dateOfBirth,
                groupId = groupId,
                thumbnailImageUrl = thumbnailImageUrl,
                kakaoId = kakaoId
            )
        }
        return remoteFriendDataSource.addFriend(param).friendId
    }

    override suspend fun updateFriend(friendId: Long, friend: UpdateFriendParams): Long {
        val param = with(friend) {
            UpdateFriendRequestBody(
                name = name,
                dateOfBirth = dateOfBirth,
                groupId = groupId,
                thumbnailImageUrl = thumbnailImageUrl,
                kakaoId = kakaoId
            )
        }
        return remoteFriendDataSource.updateFriend(friendId, param).friendId
    }
}