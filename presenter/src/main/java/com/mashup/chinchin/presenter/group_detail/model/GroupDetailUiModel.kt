package com.mashup.chinchin.presenter.group_detail.model

import com.mashup.chinchin.domain.model.Friends
import com.mashup.chinchin.domain.model.GroupDetail
import com.mashup.chinchin.presenter.common.model.FriendUiModel

data class GroupDetailUiModel(
    val id: Long,
    val groupName: String,
    val friends: List<FriendUiModel>,
)

fun GroupDetail.toUiModel(): GroupDetailUiModel {
    return GroupDetailUiModel(
        id = groupId,
        groupName = groupName,
        friends = friendInfo.map { it.toUiModel() }
    )
}

fun Friends.toUiModel(): FriendUiModel {
    return FriendUiModel(
        id = id,
        name = name,
        profileUrl = thumbnailImageUrl,
    )
}
