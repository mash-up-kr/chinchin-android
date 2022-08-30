package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Alarm
import com.mashup.chinchin.domain.model.Friend

data class UserAlarmResponseBody(
    @SerializedName("alerts") val questionAlarms: List<AlertDto>,
    @SerializedName("alertCount") val alarmCount: Int,
)

data class AlertDto(
    @SerializedName("friend") val friend: AlertFriendDto,
    @SerializedName("questionnaireId") val questionnaireId: Long,
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("type") val type: String,
) : DomainMapper<Alarm> {
    override fun toDomainModel(): Alarm {
        return Alarm(
            friend = Friend(
                id = friend.id,
                name = friend.name,
                thumbnailImageUrl = friend.thumbnailImageUrl,
            ),
            questionnaireId = questionnaireId,
            createdAt = createdAt,
            type = type,
        )
    }
}

data class AlertFriendDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("thumbnailImageUrl") val thumbnailImageUrl: String?
)
