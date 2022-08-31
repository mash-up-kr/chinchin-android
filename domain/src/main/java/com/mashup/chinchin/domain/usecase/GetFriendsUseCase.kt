package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.repository.FriendRepository
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val friendRepository: FriendRepository
) {
    suspend operator fun invoke(): List<Friend> {
        return friendRepository.getFriends()
    }
}