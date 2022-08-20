package com.mashup.chinchin.presenter.common

/**
 * 질문 카드 상태
 */
enum class ChinChinQuestionCardState: CardState {
    /**
     * 질문 편집 페이지 카드 상태 (질문 보낼 때)
     */
    SEND_EDIT_MODE, // 작성 가능한 상태 (회색)
    SEND_DELETE_MODE, // 삭제 가능한 상태 (회색)

    /**
     * 요청 답변 작성 카드 상태 (답변 할 때)
     */
    REPLY_COMPLETE, // 답변 완료 (노란색)
    REPLY_INCOMPLETE, // 답변 미완료 (회색)
}