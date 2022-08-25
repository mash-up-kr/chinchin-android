package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Friend

data class RecommendedFriendResponseBody(
    @SerializedName("id") val id: Long,
    @SerializedName("profile_nickname") val profileNickname: String,
    @SerializedName("profile_thumbnail_image") val profileThumbnailImage: String
): DomainMapper<Friend> {
    override fun toDomainModel(): Friend {
        return Friend(
            id = id,
            profileNickname = profileNickname,
            profileThumbnailImage = profileThumbnailImage,
            birth = null,
            groupName = null
        )
    }
}
