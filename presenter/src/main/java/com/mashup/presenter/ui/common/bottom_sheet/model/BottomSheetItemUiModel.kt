package com.mashup.presenter.ui.common.bottom_sheet.model


data class BottomSheetItemUiModel(
    val text: String,
    val iconResId: Int,
    val onClick: () -> Unit
)