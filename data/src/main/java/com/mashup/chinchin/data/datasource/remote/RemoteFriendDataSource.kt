package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.requestbody.AddFriendRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.UpdateFriendRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.AddFriendResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetFriendProfileResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetFriendsResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.UpdateFriendResponseBody
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

    suspend fun addFriend(addFriendRequestBody: AddFriendRequestBody): AddFriendResponseBody {
        return chinChinService.addFriend(addFriendRequestBody)
    }

    suspend fun updateFriend(friendId: Long, updateFriendRequestBody: UpdateFriendRequestBody): UpdateFriendResponseBody {
        return chinChinService.updateFriend(friendId, updateFriendRequestBody)
    }
}