package com.mashup.chinchin.data.dto.remote.responsebody.common

import com.google.gson.annotations.SerializedName
import com.mashup.chinchin.domain.base.DomainMapper
import com.mashup.chinchin.domain.model.Profile

data class ProfileResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("groupId") val groupId: Long,
    @SerializedName("name") val name: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String,
    @SerializedName("isMember") val isMember: Boolean,
    @SerializedName("thumbnailImageUrl") val thumbnailImageUrl: String?,
    @SerializedName("kakaoId") val kakaoId: String?,
) : DomainMapper<Profile> {
    override fun toDomainModel(): Profile {
        return Profile(
            id = id,
            groupId = groupId,
            name = name,
            dateOfBirth = dateOfBirth,
            isMember = isMember,
            thumbnailImageUrl = thumbnailImageUrl,
            kakaoId = kakaoId,
            groupName = "" // FIXME 은정이가 추가해줄예정
        )
    }
}