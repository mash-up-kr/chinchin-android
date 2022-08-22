package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName

data class CreateNewGroupResponseBody (
    @SerializedName("isSuccess") val isSuccess: Boolean?,
)
