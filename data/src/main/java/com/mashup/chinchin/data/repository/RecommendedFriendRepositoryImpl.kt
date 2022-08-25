package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteRecommendedFriendDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.RecommendedFriendRequestBody
import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.repository.RecommendedFriendRepository
import javax.inject.Inject

class RecommendedFriendRepositoryImpl @Inject constructor(
    private val remoteRecommendedFriendDataSource: RemoteRecommendedFriendDataSource
) : RecommendedFriendRepository {
    override suspend fun getRecommendedFriends(kakaoToken: String): List<Friend> {
        val param = RecommendedFriendRequestBody(kakaoToken)

        return remoteRecommendedFriendDataSource.getRecommendedFriends(param)
            .map { it.toDomainModel() }
    }
}