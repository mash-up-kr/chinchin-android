package com.mashup.chinchin.presenter.common

/**
 * 답변 카드 상태
 */
enum class ChinChinAnswerCardState: CardState {
    /**
     * 친구 상세 페이지에서
     * 친구답변, 내 답변 카드 상태
     */
    MY_ANSWER, // 내 답변 (회색)
    FRIEND_ANSWER, // 친구 답변(노란색)
}