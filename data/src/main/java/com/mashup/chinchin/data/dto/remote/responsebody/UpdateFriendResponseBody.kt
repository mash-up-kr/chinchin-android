package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName

data class UpdateFriendResponseBody(
    @SerializedName("friendId") val friendId: Long
)
