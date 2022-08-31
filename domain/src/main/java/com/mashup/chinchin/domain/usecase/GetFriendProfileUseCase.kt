package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.domain.repository.FriendRepository
import javax.inject.Inject

class GetFriendProfileUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(friendId: Long): FriendProfile {
        return friendRepository.getFriendProfile(friendId)
    }
}