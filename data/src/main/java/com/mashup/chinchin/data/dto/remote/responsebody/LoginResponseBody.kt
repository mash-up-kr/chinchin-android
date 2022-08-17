package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName

data class LoginResponseBody(
    @SerializedName("jwt") val jwt: String?
)
