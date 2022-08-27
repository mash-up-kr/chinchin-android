package com.mashup.chinchin.data.service

import com.mashup.chinchin.data.dto.remote.requestbody.CreateNewGroupRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.LoginRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.SendQuestionnaireRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.SendReplyQuestionnaireRequestBody
import com.mashup.chinchin.data.dto.remote.responsebody.*
import retrofit2.http.*

interface ChinChinService {
    @POST("/kakao-login")
    suspend fun login(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    @POST("groups/group")
    suspend fun createNewGroup(
        @Body createNewGroupRequestBody: CreateNewGroupRequestBody,
    ): CreateNewGroupResponseBody

    @GET("/groups")
    suspend fun getGroups(): List<GetGroupsResponseBody>

    /**
     * 질문지를 조회합니다.
     */
    @GET("/questionnaire/{questionnaire_id}")
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
    ): SendReplyQuestionnaireResponseBody

    /**
     * 친구 프로필 상세를 조회합니다.
     */
    @GET("/questionnaire/profile/{friendId}")
    suspend fun getFriendProfile(
        @Path("friendId") friendId: Long
    ): GetFriendProfileResponseBody

    /**
     * 친구에게 질문지를 전송합니다.
     */
    @POST("/questionnaire")
    suspend fun sendQuestionnaire(
        @Body sendQuestionnaireRequest: SendQuestionnaireRequestBody
    ): SendQuestionnaireResponseBody
}
