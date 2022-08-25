package com.mashup.chinchin.presenter.edit_preference

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.chinchin.presenter.common.ChinChinQuestionCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.*

//TODO 1,2문제때문에 Acitivity에 있는 viewModel을 스크린에 넣기가 어려움. 나중에 리뷰 받아서 수정
class EditPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val passedExtras: Bundle? = intent.extras
            checkNotNull(passedExtras)
            var passedQuestion: ArrayList<QuestionUiModel>? =
                passedExtras.getBundle(EXTRA_BUNDLE)?.getParcelableArrayList(EXTRA_QUESTIONS)
            val editPreferenceViewModel: EditPreferenceViewModel = viewModel()
            passedQuestion?.let {
                editPreferenceViewModel.initializeQuestions(passedQuestion) //TODO 1.요기 초기화 부분이랑
            }
            val onSendResultDataAndFinishActivity: () -> Unit = {
                val resultData = Intent().apply {
                    //TODO 2.데이터 읽어오는 부분
                    val questionsArrayList = ArrayList(editPreferenceViewModel.getCheckedQuestionsToRemove())
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(EXTRA_QUESTIONS, questionsArrayList)
                    putExtra(EXTRA_BUNDLE, bundle)
                }
                setResult(RESULT_OK, resultData)
                finish()
            }
            EditPreferenceScreen(
                onSendResultDataAndFinishActivity = onSendResultDataAndFinishActivity,
            ){
                finish()
            }
        }
    }
    companion object {
        const val EXTRA_QUESTIONS = "EXTRA_QUESTIONS"
        const val EXTRA_BUNDLE = "EXTRA_BUNDLE"
    }
}

@Composable
fun EditPreferenceScreen(
    onSendResultDataAndFinishActivity:() -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    val editPreferenceViewModel: EditPreferenceViewModel = viewModel()
    val questions = editPreferenceViewModel.questions.observeAsState().value ?: emptyList()
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        NormalDialog(
            titleText = "질문지를 삭제할까요?",
            onClickSuccess = {
                onSendResultDataAndFinishActivity()
            },
            onClickCancel = {
                showDialog.value = false
            },
        )
    }
    val onClickQuestionCard: (Int) -> Unit = { index ->
        editPreferenceViewModel.updateCheckedState(index)
    }
    val title =
        if (editPreferenceViewModel.isEmptyCheckedCard())
            "질문선택"
        else
            "${editPreferenceViewModel.getCheckedCardCount()}개의 질문 선택됨"

    Column {
        ChinChinToolbar(
            title = title,
        ) {
            onClickBackButton()
        }
        Spacer(modifier = Modifier.height(16.dp))
        EditPreferenceQuestionList(
            questions = questions,
            onClickQuestionCard = onClickQuestionCard,
        ) {
            ChinChinConfirmButton(
                buttonText = "삭제하기",
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
                isEnable = !editPreferenceViewModel.isEmptyCheckedCard(),
            ) {
                showDialog.value = true
            }
        }
    }
}

@Composable
fun EditPreferenceQuestionList(
    questions: List<QuestionUiModel>,
    onClickQuestionCard: (Int) -> Unit,
    ChinChinConfirmButton: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        ChinChinText(
            text = "총 질문",
            highlightText = "${questions.size}",
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = 16.dp
            ), //TODO lazyColumn bottomPadding 규격 정해지면 변경 해야함
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(questions) { index, question ->
                ChinChinQuestionCard(
                    index = index,
                    question = question.question,
                    answer = question.answer,
                    cardState = ChinChinQuestionCardState.SEND_DELETE_MODE,
                    isChecked = question.isChecked,
                    modifier = Modifier.clickable { onClickQuestionCard(index) },
                )
            }
        }
        ChinChinConfirmButton()
    }
}
