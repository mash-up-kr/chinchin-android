package com.mashup.chinchin.presenter.friend_detail.model

import com.mashup.chinchin.domain.model.Profile
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.common.model.GroupUiModel

data class ProfileUiModel(
    val id: Long,
    val groupId: Long,
    val groupName: String,
    val name: String,
    val dateOfBirth: String,
    val isMember: Boolean,
    val thumbnailImageUrl: String?,
) {
    // FriendUiModel에 isMember값 추가하고 통일해야할듯. 지금 여긴 없어서 임시용으로 만들어놨어요.
    fun toFriendUiModel(): FriendUiModel {
        return FriendUiModel(
            id = id,
            group = GroupUiModel(
                groupId = groupId,
                groupName = groupName
            ),
            name = name,
            birthday = dateOfBirth,
            profileUrl = thumbnailImageUrl,
        )
    }

    companion object {
        fun fromDomainModel(profile: Profile): ProfileUiModel {
            return with(profile) {
                ProfileUiModel(
                    id = id,
                    groupId = groupId,
                    name = name,
                    dateOfBirth = dateOfBirth,
                    isMember = isMember,
                    thumbnailImageUrl = thumbnailImageUrl,
                    groupName = groupName
                )
            }
        }
    }
}
