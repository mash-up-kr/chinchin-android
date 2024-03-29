package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.FriendRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(friend: AddFriendParams): Long {
        return friendRepository.addFriend(friend)
    }
}

// TODO: 더 좋은 방법이 있는지 강구할 필요가 있다..!
data class AddFriendParams(
    val name: String,
    val dateOfBirth: String,
    val groupId: Long,
    val thumbnailImageUrl: String?,
    val kakaoId: String?
)