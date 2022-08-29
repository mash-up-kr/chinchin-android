package com.mashup.chinchin.data.datasource.remote

import com.mashup.chinchin.data.dto.remote.requestbody.SendQuestionnaireRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.SendReplyQuestionnaireRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetQuestionnaireResponse
import com.mashup.chinchin.data.dto.remote.responsebody.SendQuestionnaireResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.SendReplyQuestionnaireResponseBody
import com.mashup.chinchin.data.service.ChinChinService
import javax.inject.Inject

class RemoteQuestionnaireDataSource @Inject constructor(
    private val chinChinService: ChinChinService
) {
    suspend fun getQuestionnaire(
        questionnaireId: Long,
        aspect: String
    ): GetQuestionnaireResponse {
        return chinChinService.getQuestionnaire(questionnaireId = questionnaireId, aspect)
    }

    suspend fun sendReplyQuestionnaire(
        questionnaireId: Long,
        requestBody: List<SendReplyQuestionnaireRequestBody>
    ): SendReplyQuestionnaireResponseBody {
        return chinChinService.sendReplyQuestionnaire(
            questionnaireId = questionnaireId,
            sendReplyQuestionnaireRequestBody = requestBody
        )
    }

    suspend fun sendQuestionnaire(
        sendQuestionnaireRequestBody: SendQuestionnaireRequestBody
    ): SendQuestionnaireResponseBody {
        return chinChinService.sendQuestionnaire(sendQuestionnaireRequestBody)
    }
}