package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.data.dto.remote.responsebody.common.ProfileResponse
import com.mashup.chinchin.data.dto.remote.responsebody.common.QuestionResponse
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.FriendProfile

data class GetFriendProfileResponseBody(
    @SerializedName("profile") val profile: ProfileResponse,
    @SerializedName("myAnswer") val myAnswers: List<QuestionResponse>,
    @SerializedName("friendAnswer") val friendAnswers: List<QuestionResponse>,
) : DomainMapper<FriendProfile> {
    override fun toDomainModel(): FriendProfile {
        return FriendProfile(
            profile = profile.toDomainModel(),
            myAnswers = myAnswers.map { it.toDomainModel() },
            friendAnswers = friendAnswers.map { it.toDomainModel() }
        )

    }
}