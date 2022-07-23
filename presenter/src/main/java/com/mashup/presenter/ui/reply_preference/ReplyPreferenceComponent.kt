package com.mashup.presenter.ui.reply_preference

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mashup.presenter.reply_preference.model.ReplyPreferenceQuestionUiModel
import com.mashup.presenter.ui.common.ChinChinText

@Composable
fun ReplyPreferenceQuestionList(modifier: Modifier = Modifier, questions: List<ReplyPreferenceQuestionUiModel>) {
    Column(modifier = modifier) {
        ChinChinText(text = "총 질문", highlightText = "4")
        LazyColumn {
            items(questions) { item ->

            }
        }
    }
}
