package com.mashup.presenter.ui.common.bottom_sheet.model

//TODO sealed class로 만들어야됨
data class BottomSheetItemUiModel(
    val text: String,
    val iconResId: Int,
    val onClick: () -> Unit, //Todo 신규 친구 추가하기는 람다 파라미터 변경해야함
)