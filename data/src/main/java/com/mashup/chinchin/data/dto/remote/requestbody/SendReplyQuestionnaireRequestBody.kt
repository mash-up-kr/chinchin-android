package com.mashup.chinchin.data.dto.remote.requestbody

import com.google.gson.annotations.SerializedName

data class SendReplyQuestionnaireRequestBody(
    @SerializedName("questionId") val questionId: Long,
    @SerializedName("friendAnswer") val friendAnswer: String
)