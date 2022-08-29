package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.data.dto.remote.responsebody.common.FriendInfoResponse
import com.mashup.chinchin.domain.model.GroupDetail

data class GetFriendsResponseBody(
    @SerializedName("groupId") val groupId: Long,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("friendInfo") val friendInfo: List<FriendInfoResponse>,
) {
    fun toDomainModel(): GroupDetail {
        return GroupDetail(
            groupId = groupId,
            groupName = groupName,
            friendInfo = friendInfo.map { it.toDomainModel() },
        )
    }
}