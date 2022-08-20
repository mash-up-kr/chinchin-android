package com.mashup.chinchin.presenter.ui.send_questions

import QuestionCategoryDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.common.ChinChinQuestionCardState
import com.mashup.chinchin.presenter.common.model.CategoryUiModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinQuestionCard
import com.mashup.chinchin.presenter.ui.common.ChinChinText
import com.mashup.chinchin.presenter.ui.theme.Gray_500
import com.mashup.chinchin.presenter.ui.theme.Gray_800

@Composable
fun SendPreferenceQuestionTitle(userName: String) {
    Column {
        Row {
            Text(
                text = userName,
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "에게 물어볼",
                color = Gray_500,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(modifier = Modifier.padding(top = 2.dp)) {
            Text(
                text = "키워드를 선택",
                color = Gray_800,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "해주세요!",
                color = Gray_500,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun QuestionCategoryList(
    categories: List<CategoryUiModel> = listOf(),
    addQuestion: (QuestionUiModel) -> Boolean
) {
    val selectedCategory = remember { mutableStateOf(CategoryUiModel("", emptyList())) }
    val showDialog = remember { mutableStateOf(false) }
    val selectedKeyword = remember { mutableStateOf("") }

    if (showDialog.value) {
        QuestionCategoryDialog(
            category = selectedCategory.value.category,
            selectedKeyword = selectedKeyword.value,
            keywords = selectedCategory.value.keywords.map { it.keyword },
            onClick = { keyword ->
                selectedKeyword.value = keyword

                val selectedQuestion = selectedCategory.value.keywords.find { it.keyword == keyword }?.question
                addQuestion(
                    QuestionUiModel(selectedQuestion ?: throw Exception("선택된 키워드가 질문리스트에 존재하지 않습니다."))
                )
            }
        ) {
            selectedKeyword.value = ""
            showDialog.value = false
        }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (category in categories) {
            QuestionCategoryChip(
                category = category.category,
                addQuestion = addQuestion,
                onClickCategory = { category ->
                    selectedCategory.value = categories.find { it.category == category } ?: return@QuestionCategoryChip
                    showDialog.value = true
                })
        }
        QuestionCategoryChip(category = "자유 질문", addQuestion = addQuestion)
    }
}

@Composable
fun QuestionCategoryChip(
    category: String,
    onClickCategory: (String) -> Unit = {},
    addQuestion: (QuestionUiModel) -> Boolean
) {
    val emptyQuestion = QuestionUiModel(question = "질문을 적어보세요.")
    val freeQuestion = "자유 질문"

    OutlinedButton(
        onClick = {
            if (category == freeQuestion) addQuestion(emptyQuestion) else onClickCategory(
                category
            )
        },
        shape = RoundedCornerShape(16.dp),
        border = ButtonDefaults.outlinedBorder.copy(brush = SolidColor(Gray_500)),
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .wrapContentWidth()
            .height(32.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
    ) {
        Text(
            text = category,
            color = Gray_500,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SendPreferenceQuestionList(
    modifier: Modifier,
    questions: List<QuestionUiModel> = listOf(),
) {
    Column(modifier = modifier) {
        ChinChinText(text = "총 질문", highlightText = "${questions.size}")
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp),
        ) {
            itemsIndexed(questions) { index, question ->
                val (questionText, setQuestionText) = remember { mutableStateOf(question.question) }
                val (answer, setAnswer) = remember { mutableStateOf(question.answer) }

                ChinChinQuestionCard(
                    index = index,
                    question = questionText,
                    onQuestionChanged = setQuestionText,
                    answer = answer,
                    onAnswerChanged = setAnswer,
                    cardState = ChinChinQuestionCardState.SEND_EDIT_MODE
                )
            }
        }
    }
}
