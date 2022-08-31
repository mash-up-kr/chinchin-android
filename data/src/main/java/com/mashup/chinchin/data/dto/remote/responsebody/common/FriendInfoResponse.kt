package com.mashup.chinchin.data.dto.remote.responsebody.common

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.model.Friend

data class FriendInfoResponse(
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