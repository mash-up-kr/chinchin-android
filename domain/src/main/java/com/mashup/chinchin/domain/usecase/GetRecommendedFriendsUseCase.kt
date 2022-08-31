package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.repository.LoginRepository
import com.mashup.chinchin.domain.repository.RecommendedFriendRepository
import javax.inject.Inject

class GetRecommendedFriendsUseCase @Inject constructor(
    private val recommendedFriendRepository: RecommendedFriendRepository,
    private val loginRepository: LoginRepository,
) {
    suspend operator fun invoke(): List<Friend> {
        val kakaoToken: String = loginRepository.getKakaoAccessToken()
        return recommendedFriendRepository.getRecommendedFriends(kakaoToken)
    }
}
