package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.repository.FriendProfileRepository
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val friendProfileRepository: FriendProfileRepository
){
    suspend operator fun invoke(friend: Friend): Long {
        return friendProfileRepository.addFriend(friend)
    }
}