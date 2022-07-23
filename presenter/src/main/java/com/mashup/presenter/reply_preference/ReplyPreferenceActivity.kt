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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.common.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.reply_preference.ReplyPreferenceQuestionList
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.Gray_600
import com.mashup.presenter.ui.theme.Gray_800


class ReplyPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                ReplyPreferenceScreen(
                    questions = List(14) {
                        QuestionUiModel("good $it", "답변답변답변")
                    }
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
    onActivityFinish: () -> Unit = {},
) {
    Column {
        ChinChinToolbar(title = "취향 질문 답변하기") {
            onActivityFinish()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            /* TODO: 유저에 따라 이름 적용 */
            Text(
                text = "영은 님이 보낸 질문에\n나의 취향을 답변해보세요!",
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
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
