package com.mashup.chinchin.presenter.edit_preference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.common.ChinChinQuestionCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinConfirmButton
import com.mashup.chinchin.presenter.ui.common.ChinChinQuestionCard
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar

class EditPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val passedIntent: Bundle? = intent.extras
            var question: ArrayList<QuestionUiModel>? = null
            passedIntent?.let {
                question = it.getBundle("bundle")?.getParcelableArrayList("questions")
            }
            question?.let {
                EditPreferenceScreen(it.toList()) {

                }
            }
        }
    }
}

@Composable
fun EditPreferenceScreen(
    questionUiModels: List<QuestionUiModel>,
    onConfirmButtonClick: () -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    Column {
        ChinChinToolbar(
            title = "질문 선택", // TODO 툴바 상태 변하기
        ) {
            onClickBackButton()
        }
        Spacer(modifier = Modifier.height(16.dp))
        EditPreferenceQuestionList(
            questionUiModels = questionUiModels
        ) {
            ChinChinConfirmButton(
                buttonText = "삭제하기",
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
            ) {
                onConfirmButtonClick()
            }
        }

    }
}

@Composable
fun EditPreferenceQuestionList(
    questionUiModels: List<QuestionUiModel>,
    ChinChinConfirmButton: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        ChinChinText(
            text = "총 질문",
            highlightText = "${questionUiModels.size}",
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 16.dp
            ), //TODO lazyColumn bottomPadding 규격 정해지면 변경 해야함
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(questionUiModels) { index, questionUiModel ->
                ChinChinQuestionCard(
                    index = index,
                    question = questionUiModel.question,
                    answer = questionUiModel.answer,
                    cardState = ChinChinQuestionCardState.DELETE_MODE,
                )
            }
        }
        ChinChinConfirmButton()
    }
}
