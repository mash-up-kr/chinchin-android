package com.mashup.presenter.ui.reply_preference

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.presenter.common.ChinChinQuestionCardState
import com.mashup.presenter.common.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinQuestionCard
import com.mashup.presenter.ui.common.ChinChinText

@Composable
fun ReplyPreferenceQuestionList(modifier: Modifier = Modifier, questions: List<QuestionUiModel>) {
    Column(modifier = modifier) {
        ChinChinText(text = "총 질문", highlightText = "${questions.size}")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp),
        ) {
            itemsIndexed(questions) { index, question ->
                ChinChinQuestionCard(
                    index = index,
                    question = question.question,
                    answer = question.answer,
                    /* TODO: 카드 타입 테스트입니다 정상 로직으로 교체 예정.. */
                    cardState = when (index % 3) {
                        0 -> ChinChinQuestionCardState.FRIEND_REPLY
                        1 -> ChinChinQuestionCardState.EXPECT_INCOMPLETE_REPLY
                        else -> ChinChinQuestionCardState.EXPECT_COMPLETE_REPLY
                    }
                )
            }
        }
    }
}
