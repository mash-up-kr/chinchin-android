package com.mashup.chinchin.domain.model

data class Groups(
    val groups: List<GroupInfo>
)

data class GroupInfo(
    val groupId: Long,
    val groupName: String,
    val groupMemberCount: Int,
    val thumbnailImageUrls: List<String>,
)

data class GroupDetail(
    val groupId: Long,
    val groupName: String,
    val friendInfo: List<Friends>,
)

data class Friends(
    val id: Long,
    val name: String,
    val thumbnailImageUrl: String,
)