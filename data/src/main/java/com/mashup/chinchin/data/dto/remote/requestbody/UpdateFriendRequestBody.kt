package com.mashup.chinchin.data.dto.remote.requestbody

import com.google.gson.annotations.SerializedName

data class UpdateFriendRequestBody(
    @SerializedName("name") val name: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String,
    @SerializedName("groupId") val groupId: Long,
    @SerializedName("thumbnailImageUrl") val thumbnailImageUrl: String?,
    @SerializedName("kakaoId") val kakaoId: String?,
)
