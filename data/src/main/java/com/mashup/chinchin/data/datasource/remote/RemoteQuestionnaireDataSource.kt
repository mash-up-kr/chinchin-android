package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.responsebody.GetQuestionnaireResponse
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteQuestionnaireDataSource @Inject constructor(
    private val chinChinService: ChinChinService
) {
    suspend fun getQuestionnaire(
        questionnaireId: Long,
        aspect: String
    ): List<GetQuestionnaireResponse> {
        return chinChinService.getQuestionnaire(questionnaireId = questionnaireId, aspect)
    }
}