package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.FriendProfileRepository
import javax.inject.Inject

class UpdateFriendUseCase @Inject constructor(
    private val friendProfileRepository: FriendProfileRepository
) {
    suspend operator fun invoke(friend: UpdateFriendParams): Long {
        return friendProfileRepository.updateFriend(friend)
    }
}

data class UpdateFriendParams(
    val name: String,
    val dateOfBirth: String,
    val groupId: Long,
    val thumbnailImageUrl: String?,
    val kakaoId: String?
)