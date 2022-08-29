package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.model.Friend
import com.mashup.chinchin.domain.model.GroupDetail

data class GetFriendsInGroupResponseBody(
    @SerializedName("groupId") val groupId: Long,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("friendInfo") val friendInfo: List<FriendInfo>,
) {
    fun toDomainModel(): GroupDetail {
        return GroupDetail(
            groupId = groupId,
            groupName = groupName,
            friendInfo = friendInfo.map { it.toDomainModel() },
        )
    }
}

data class FriendInfo(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnailImageUrl") val thumbnailImageUrl: String,
) {
    fun toDomainModel(): Friend {
        return Friend(
            id = id,
            name = name,
            thumbnailImageUrl = thumbnailImageUrl,
        )
    }
}
