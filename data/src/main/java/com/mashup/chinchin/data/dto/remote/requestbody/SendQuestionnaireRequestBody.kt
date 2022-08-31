package com.mashup.chinchin.data.dto.remote.requestbody

import com.google.gson.annotations.SerializedName

data class SendQuestionnaireRequestBody(
    @SerializedName("toFriendId") val toFriendId: Long,
    @SerializedName("questionnaireDetails") val questionnaireDetails: List<QuestionRequestBody>
)

data class QuestionRequestBody(
    @SerializedName("question") val question: String,
    @SerializedName("myAnswer") val myAnswer: String
)
