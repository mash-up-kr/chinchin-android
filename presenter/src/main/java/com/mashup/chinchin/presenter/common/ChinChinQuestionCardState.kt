package com.mashup.chinchin.presenter.common

//Todo 상태 정리 필요함
enum class ChinChinQuestionCardState {
    FRIEND_REPLY,
    INPUT_COMPLETE,

    EXPECT_COMPLETE_REPLY,

    EXPECT_INCOMPLETE_REPLY,
    INPUT_INCOMPLETE,

    EDIT_MODE,
    DELETE_MODE,
}
