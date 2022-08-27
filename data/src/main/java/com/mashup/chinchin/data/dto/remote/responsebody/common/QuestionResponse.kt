package com.mashup.chinchin.data.dto.remote.responsebody.common

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Question

data class QuestionResponse(
    @SerializedName("questionId") val questionId: Long,
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String
) : DomainMapper<Question> {
    override fun toDomainModel(): Question {
        return Question(
            questionId = questionId,
            question = question,
            answer = answer
        )
    }
}
