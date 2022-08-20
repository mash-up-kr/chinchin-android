package com.mashup.chinchin.presenter.edit_preference

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.chinchin.presenter.common.ChinChinQuestionCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.*

//TODO 27Line 데이터가 없으면 해당 액티비티가 실행되지 않아야 한다고 생각해서 이렇게 리턴했는데 의견 부탁드립니다.
class EditPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val passedExtras: Bundle = intent.extras ?: return@setContent
            var question: ArrayList<QuestionUiModel>? = passedExtras.getBundle(EXTRA_BUNDLE)?.getParcelableArrayList(EXTRA_QUESTIONS)
            val editPreferenceViewModel: EditPreferenceViewModel = viewModel()
            question?.let {
                editPreferenceViewModel.initializeQuestions(it)
            }
            val onSendResultDataAndFinishActivity : () -> Unit = {
                val resultData = Intent().apply{
                    editPreferenceViewModel.removeCheckedQuestions()
                    val questionsArrayList = ArrayList(editPreferenceViewModel.questions.toList())
                    val bundle = Bundle()
                    bundle.putParcelableArrayList(EXTRA_QUESTIONS,questionsArrayList)
                    putExtra(EXTRA_BUNDLE,bundle)
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
    editPreferenceViewModel: EditPreferenceViewModel = viewModel(),
    onSendResultDataAndFinishActivity:() -> Unit = {},
    onClickBackButton: () -> Unit = {},
) {
    val showDialog = remember { mutableStateOf(false)}
    val onClickedSuccess: () -> Unit ={
        onSendResultDataAndFinishActivity()
    }
    val onClickedCancel: () -> Unit ={
        showDialog.value = false
    }
    val onConfirmButtonClick: () -> Unit ={
        showDialog.value = true
    }
    if(showDialog.value){
        NormalDialog(
            titleText="질문지를 삭제할까요?",
            onClickSuccess=onClickedSuccess,
            onClickCancel =onClickedCancel,
        )
    }
    val onClickQuestionCard: (Int) -> Unit = { index ->
        editPreferenceViewModel.updateCheckedState(index)
        Log.i("hyejin", "CreateQuestionSheetScreen: $index")
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
            questions = editPreferenceViewModel.questions,
            onClickQuestionCard = onClickQuestionCard,
        ) {
            ChinChinConfirmButton(
                buttonText = "삭제하기",
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
                isEnable = !editPreferenceViewModel.isEmptyCheckedCard(),
            ) {
                onConfirmButtonClick()
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
