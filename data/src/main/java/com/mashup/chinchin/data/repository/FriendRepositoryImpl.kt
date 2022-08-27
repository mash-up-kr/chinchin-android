package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteFriendDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.AddFriendRequestBody
import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.domain.repository.FriendRepository
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

    override suspend fun addFriend(friend: Friend): Long {
        val param = with(friend) {
            AddFriendRequestBody(
                name = name ?: "",
                dateOfBirth = dateOfBirth ?: "",
                groupId = groupId ?: -1,
                thumbnailImageUrl = thumbnailImageUrl,
                kakaoId = kakaoId
            )
        }
        return remoteFriendDataSource.addFriend(param).friendId
    }
}