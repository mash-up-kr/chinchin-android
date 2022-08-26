package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteFriendProfileDataSource
import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.domain.repository.FriendProfileRepository
import javax.inject.Inject

class FriendProfileRepositoryImpl @Inject constructor(
    private val remoteFriendProfileDataSource: RemoteFriendProfileDataSource
) : FriendProfileRepository {
    override suspend fun getFriendProfile(friendId: Long): FriendProfile {
        return remoteFriendProfileDataSource.getFriendProfile(friendId).toDomainModel()
    }
}