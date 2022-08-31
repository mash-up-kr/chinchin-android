package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.model.GroupInfo
import com.mashup.chinchin.domain.model.Groups

data class GetGroupsResponseBody(
    @SerializedName("groupId") val groupId: Long,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("groupMemberCount") val groupMemberCount: Int,
    @SerializedName("thumbnailImageUrls") val thumbnailImageUrls: List<String>,
) {
    fun toDomainModel(): GroupInfo {
        return GroupInfo(
            groupId = groupId,
            groupName = groupName,
            groupMemberCount = groupMemberCount,
            thumbnailImageUrls = thumbnailImageUrls
        )
    }
}
