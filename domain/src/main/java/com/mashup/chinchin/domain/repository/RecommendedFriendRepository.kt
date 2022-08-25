package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.Friend

interface RecommendedFriendRepository {
    suspend fun getRecommendedFriends(kakaoToken: String): List<Friend>
}