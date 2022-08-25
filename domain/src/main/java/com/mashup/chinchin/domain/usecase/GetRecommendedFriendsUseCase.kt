package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.repository.RecommendedFriendRepository
import javax.inject.Inject

class GetRecommendedFriendsUseCase @Inject constructor(
    private val recommendedFriendRepository: RecommendedFriendRepository
) {
    suspend operator fun invoke(kakaoToken: String): List<Friend> {
        return recommendedFriendRepository.getRecommendedFriends(kakaoToken)
    }
}