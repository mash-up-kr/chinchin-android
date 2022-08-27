package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.domain.repository.FriendProfileRepository
import javax.inject.Inject

class GetFriendProfileUseCase @Inject constructor(
    private val friendProfileRepository: FriendProfileRepository
) {
    suspend operator fun invoke(friendId: Long): FriendProfile {
        return friendProfileRepository.getFriendProfile(friendId)
    }
}