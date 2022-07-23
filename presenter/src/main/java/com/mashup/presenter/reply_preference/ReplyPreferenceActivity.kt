package com.mashup.presenter.reply_preference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.common.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.reply_preference.ReplyPreferenceQuestionList
import com.mashup.presenter.ui.reply_preference.ReplyPreferenceTitle
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.Gray_600


class ReplyPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = MutableList(14) {
            QuestionUiModel("good $it", "답변답변답변")
        }
        list.add(0, QuestionUiModel("빈 답변"))

        setContent {
            ChinchinTheme {
                ReplyPreferenceScreen(
                    questions = list
                ) {
                    finish()
                }
            }
        }
    }
}

@Composable
fun ReplyPreferenceScreen(
    questions: List<QuestionUiModel> = listOf(),
    userName: String = "영은",
    onBackButtonClick: () -> Unit = {},
) {
    Column {
        ChinChinToolbar(title = "취향 질문 답변하기", isActiveConfirmButton = true) {
            onBackButtonClick()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            ReplyPreferenceTitle(userName)
            Text(
                text = "친구가 쓴 예상답변을 눌러서 수정해보세요",
                color = Gray_600,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp),
            )

            ReplyPreferenceQuestionList(
                questions = questions,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}
