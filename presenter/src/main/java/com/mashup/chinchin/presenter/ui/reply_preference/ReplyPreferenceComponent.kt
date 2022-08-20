package com.mashup.chinchin.presenter.ui.reply_preference

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.common.ChinChinQuestionCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinQuestionCard
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Gray_800

@Composable
fun ReplyPreferenceTitle(userName: String) {
    Column {
        Row {
            Text(
                text = userName,
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "님이 보낸 질문에",
                color = Gray_500,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(modifier = Modifier.padding(top = 2.dp)) {
            Text(
                text = "나의 취향을 답변",
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "해보세요!",
                color = Gray_500,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

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
                        0 -> ChinChinQuestionCardState.SEND_EDIT_MODE
                        1 -> ChinChinQuestionCardState.SEND_DELETE_MODE
                        else -> ChinChinQuestionCardState.REPLY_COMPLETE
                    }
                )
            }
        }
    }
}
