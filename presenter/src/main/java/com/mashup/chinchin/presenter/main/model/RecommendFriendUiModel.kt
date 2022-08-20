package com.mashup.chinchin.presenter.main.model

data class RecommendFriendUiModel(
    val id: Long,
    val profileUrl: String,
    val name: String,
) {
    /**
     * RecommendFriendUiModel을 따로 만든 이유가 있을 수 있다고 생각해서
     * mapping 함수 따로 만들었습니다.
     */
    fun toFriendUiModel(): FriendUiModel {
        return FriendUiModel(
            id = id,
            profileThumbnailUrl = profileUrl,
            name = name
        )
    }
}
