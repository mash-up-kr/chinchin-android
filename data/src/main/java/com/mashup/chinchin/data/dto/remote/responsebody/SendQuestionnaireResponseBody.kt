package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName

data class SendQuestionnaireResponseBody(
    @SerializedName("isSuccess") val isSuccess: Boolean,)
