package com.mashup.presenter.send_preference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.common.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.send_questions.QuestionCategoryList
import com.mashup.presenter.ui.send_questions.SendPreferenceQuestionList
import com.mashup.presenter.ui.send_questions.SendPreferenceQuestionTitle
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.Gray_600

class SendPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                CreateQuestionSheetScreen(
                    categories = listOf("취향 키워드", "개인정보", "자유질문")
                ) {
                    finish()
                }
            }
        }
    }
}

@Composable
fun CreateQuestionSheetScreen(
    categories: List<String> = listOf(),
    questions: List<QuestionUiModel> = listOf(),
    userName: String = "윤혜",
    onBackButtonClick: () -> Unit = {},
) {
    Column {
        ChinChinToolbar(title = "취향 질문 보내기") { // TODO: isActiveConfirmButton추가된 ChinChinToolBar 받아서 완료버튼 추가
            onBackButtonClick()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            SendPreferenceQuestionTitle(userName)
            Text(
                text = "아래 키워드를 선택해 질문을 구성할 수 있습니다.",
                color = Gray_600,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
            QuestionCategoryList(categories)
            Spacer(modifier = Modifier.height(13.dp))
            SendPreferenceQuestionList(
                questions = questions,
            )
        }
    }
}
