package com.mashup.chinchin.presenter.common.model

import android.os.Parcelable
import com.mashup.chinchin.domain.model.Friend
import kotlinx.parcelize.Parcelize

/**
 * default value 설정하는 이유: remember mutableStateOf 에서 비어있는 상태를 null로 설정할 수 없는데
 * 거기서 빈 값으로 넣어주기 싫어서 설정했습니다.
 * 더 좋은 방법이 있으면 고쳐주세요.
 */
@Parcelize
data class FriendUiModel(
    val id: Long? = null, // 친구 등록 전엔 id가 없습니다.
    val name: String?,
    val birthday: String?,
    val group: GroupUiModel?,
    val profileUrl: String? = null, // 친구 프로필은 nullable합니다.
    val kakaoId: String? = null // 미등록 친구일 경우에도 nullable합니다.
) : Parcelable

fun Friend.toUiModel(): FriendUiModel {
    return FriendUiModel(
        id = id,
        name = name,
        profileUrl = thumbnailImageUrl,
        birthday = null,
        group = null
    )
}