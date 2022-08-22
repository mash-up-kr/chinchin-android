package com.mashup.chinchin.data.dto.remote.requestbody

import com.google.gson.annotations.SerializedName

data class CreateNewGroupRequestBody (
    @SerializedName("name") val groupName: String?,
)