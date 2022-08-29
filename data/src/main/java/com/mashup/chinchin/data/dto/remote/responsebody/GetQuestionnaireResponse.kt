package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.data.dto.remote.responsebody.common.QuestionResponse
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.model.ReplyQuestionnaire

data class GetQuestionnaireResponse(
    @SerializedName("memberName") val memberName: String,
    @SerializedName("questionnaire") val questionnaire: List<QuestionResponse>
): DomainMapper<ReplyQuestionnaire> {
    override fun toDomainModel(): ReplyQuestionnaire {
        return ReplyQuestionnaire(
            memberName = memberName,
            questionnaire = questionnaire.map { it.toDomainModel() }
        )
    }
}