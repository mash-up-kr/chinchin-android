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
            itemsIndexed(questions) { index, item ->
                ChinChinQuestionCard(index = index, questionUiModel = item)
            }
        }
    }
}
