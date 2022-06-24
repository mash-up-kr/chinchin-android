package com.mashup.data.dto.remote

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("followers") val followers: String?,
    @SerializedName("following") val following: String?
)