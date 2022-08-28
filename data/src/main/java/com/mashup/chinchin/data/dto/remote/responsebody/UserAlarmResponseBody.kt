package com.mashup.chinchin.data.dto.remote.responsebody

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Alarm

data class UserAlarmResponseBody(
    @SerializedName("alerts") val questionAlarms: List<AlertDto>,
    @SerializedName("alertCount") val alarmCount: Int,
)

data class AlertDto(
    @SerializedName("friendId") val friendId: Long,
    @SerializedName("questionnaireId") val questionnaireId: Long,
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("type") val type: String,
) : DomainMapper<Alarm> {
    override fun toDomainModel(): Alarm {
        return Alarm(
            friendId = friendId,
            questionnaireId = questionnaireId,
            createdAt = createdAt,
            type = type,
        )
    }
}
