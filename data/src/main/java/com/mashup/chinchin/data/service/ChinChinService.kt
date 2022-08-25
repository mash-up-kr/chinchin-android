package com.mashup.chinchin.data.service

import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.LoginRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.RecommendedFriendRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.SendReplyQuestionnaireRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.CreateNewGroupResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.GetQuestionnaireResponse
import com.mashup.chinchin.data.dto.remote.responsebody.LoginResponseBody
import com.mashup.chinchin.data.dto.remote.responsebody.RecommendedFriendResponseBody
import retrofit2.http.*

interface ChinChinService {
    @POST("/kakao-login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    @POST("groups/group")
    suspend fun createNewGroup(
        @Header("Authorization") jwt: String,
        @Body createNewGroupRequestBody: CreateNewGroupRequestBody,
    ): CreateNewGroupResponseBody

    /**
     * 질문지를 조회합니다.
     */
    @POST("/questionnaire/{questionnaire_id}")
    suspend fun getQuestionnaire(
        @Path("questionnaire_id") questionnaireId: Long,
        @Query("aspect") aspect: String
    ): List<GetQuestionnaireResponse>

    /**
     * 질문에 대한 답변을 보냅니다.
     */
    @PUT("/questionnaire/{questionnaire_id}")
    suspend fun sendReplyQuestionnaire(
        @Path("questionnaire_id") questionnaireId: Long,
        @Body sendReplyQuestionnaireRequestBody: List<SendReplyQuestionnaireRequestBody>
    ): Boolean

    @GET("/members/recommended-friends")
    suspend fun getRecommendedFriends(
        @Body recommendedFriendRequestBody: RecommendedFriendRequestBody
    ): List<RecommendedFriendResponseBody>
}
