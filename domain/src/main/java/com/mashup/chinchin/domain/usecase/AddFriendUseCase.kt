package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.FriendProfileRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val friendProfileRepository: FriendProfileRepository
) {
    suspend operator fun invoke(friend: AddFriendParams): Long {
        return friendProfileRepository.addFriend(friend)
    }
}

data class AddFriendParams(
    val name: String,
    val dateOfBirth: String,
    val groupId: Long,
    val thumbnailImageUrl: String?,
    val kakaoId: String?
)