package com.mashup.chinchin.presenter.main.model

import android.os.Parcelable
import com.mashup.chinchin.domain.model.Groups
import kotlinx.parcelize.Parcelize

@Parcelize
data class FriendGroupUiModel(
    val groups: List<GroupInfoUiModel>,
): Parcelable

@Parcelize
data class GroupInfoUiModel(
    val groupId: Long,
    val groupName: String,
    val groupMemberCount: Int,
    val thumbnailImageUrls: List<String>,
): Parcelable

fun Groups.toUiModel(): FriendGroupUiModel {
    return FriendGroupUiModel(
        groups.map {
            GroupInfoUiModel(
                groupId = it.groupId,
                groupName = it.groupName,
                groupMemberCount = it.groupMemberCount,
                thumbnailImageUrls = it.thumbnailImageUrls
            )
        }
    )
}
