package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName

data class IsAlarmResponseBody(
    @SerializedName("alertExistence") val isAlarmExist: Boolean,
)
