package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.ChinChinRepository
import javax.inject.Inject

class InsertFriendToDBUseCase @Inject constructor(
    private val chinChinRepository: ChinChinRepository,
) {
    suspend operator fun invoke(friend: InsertFriendToDBParams) =
        chinChinRepository.insertFriend(friend)

}

data class InsertFriendToDBParams(
    val friendId: Long,
    val name: String,
    val dateOfBirth: String,
    val groupId: Long,
    val thumbnailImageUrl: String? = null,
    val kakaoId: String? = null,
)