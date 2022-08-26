package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName

data class SendReplyQuestionnaireResponseBody(
    @SerializedName("isSuccess") val isSuccess: Boolean,
)
