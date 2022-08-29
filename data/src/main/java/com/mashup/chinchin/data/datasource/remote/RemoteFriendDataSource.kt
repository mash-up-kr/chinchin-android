package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.responsebody.GetFriendProfileResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetFriendsResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteFriendDataSource @Inject constructor(
    private val chinChinService: ChinChinService
) {
    suspend fun getFriendProfile(friendId: Long): GetFriendProfileResponseBody {
        return chinChinService.getFriendProfile(friendId)
    }

    suspend fun getFriends(): GetFriendsResponseBody {
        return chinChinService.getFriends()
    }
}