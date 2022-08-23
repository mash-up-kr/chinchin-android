package com.mashup.chinchin.presenter.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 친구 Ui Model (임시)
 * FIXME: 백엔드 모델 나오면 변경
 * default value 설정하는 이유: remember mutableStateOf 에서 비어있는 상태를 null로 설정할 수 없는데
 * 거기서 빈 값으로 넣어주기 싫어서 설정했습니다.
 * 더 좋은 방법이 있으면 고쳐주세요.
 */
@Parcelize
data class FriendUiModel(
    val id: Long = -1,
    val name: String = "",
    val profileUrl: String = "",
): Parcelable
