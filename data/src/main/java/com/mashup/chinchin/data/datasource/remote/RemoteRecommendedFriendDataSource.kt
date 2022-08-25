package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.requestbody.RecommendedFriendRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.RecommendedFriendResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteRecommendedFriendDataSource @Inject constructor(
    private val chinChinService: ChinChinService
) {
    suspend fun getRecommendedFriends(
        recommendedFriendRequestBody: RecommendedFriendRequestBody
    ): List<RecommendedFriendResponseBody> {
        return chinChinService.getRecommendedFriends(recommendedFriendRequestBody)
    }
}