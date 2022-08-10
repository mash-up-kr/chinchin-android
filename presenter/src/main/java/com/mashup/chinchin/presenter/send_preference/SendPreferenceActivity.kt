package com.mashup.chinchin.presenter.send_preference

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presenter.R
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
import com.mashup.chinchin.presenter.ui.send_questions.QuestionCategoryList
import com.mashup.chinchin.presenter.ui.send_questions.SendPreferenceQuestionList
import com.mashup.chinchin.presenter.ui.send_questions.SendPreferenceQuestionTitle
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.Gray_600
import kotlinx.coroutines.launch

class SendPreferenceActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                val coroutineScope = rememberCoroutineScope()

                val modalBottomSheetState =
                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

                val showBottomSheet: () -> Unit = {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
                    }
                }

                val closeBottomSheet: () -> Unit = {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                }

                BackHandler(enabled = modalBottomSheetState.isVisible) {
                    closeBottomSheet()
                }

                ModalBottomSheetLayout(
                    sheetState = modalBottomSheetState,
                    sheetContent = {
                        BottomSheetContent(
                            "다음 단계를 선택해주세요", listOf(
                                BottomSheetItemUiModel("저장하기", R.drawable.ic_save) {
                                    closeBottomSheet() //TODO 저장로직 구현해야함
                                },
                                BottomSheetItemUiModel("취소", R.drawable.ic_x) {
                                    closeBottomSheet()
                                },
                            )
                        )
                    },
                    sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ) {
                    CreateQuestionSheetScreen(
                        categories = listOf("취향 키워드", "개인정보", "자유질문"),
                        onConfirmButtonClick = showBottomSheet,
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
        onConfirmButtonClick: () -> Unit = {},
        onBackButtonClick: () -> Unit = {},
    ) {
        Column {
            ChinChinToolbar(
                title = "취향 질문 보내기",
                isActiveConfirmButton = true,
                isAbleConfirmButton = true, //TODO 활성화 기준 정해야함
                onConfirmButtonClick = onConfirmButtonClick,
            ) {
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

}
