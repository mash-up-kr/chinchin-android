package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.FriendRepository
import javax.inject.Inject

class UpdateFriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(friend: UpdateFriendParams): Long {
        return friendRepository.updateFriend(friend)
    }
}

// TODO: 더 좋은 방법이 있는지 강구할 필요가 있다..!
data class UpdateFriendParams(
    val name: String,
    val dateOfBirth: String,
    val groupId: Long,
    val thumbnailImageUrl: String?,
    val kakaoId: String?
)