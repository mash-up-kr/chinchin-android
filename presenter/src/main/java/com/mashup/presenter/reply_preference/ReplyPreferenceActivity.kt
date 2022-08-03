package com.mashup.presenter.reply_preference

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
import com.mashup.presenter.common.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
import com.mashup.presenter.ui.reply_preference.ReplyPreferenceQuestionList
import com.mashup.presenter.ui.reply_preference.ReplyPreferenceTitle
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.Gray_600
import kotlinx.coroutines.launch


class ReplyPreferenceActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = MutableList(14) {
            QuestionUiModel("good $it", "답변답변답변")
        }
        list.add(0, QuestionUiModel("빈 답변"))

        setContent {
            ChinchinTheme {
                val modalBottomSheetState =
                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
                val coroutineScope = rememberCoroutineScope()

                val closeBottomSheet: () -> Unit = {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
                    }
                }
                val showBottomSheet: () -> Unit = {
                    coroutineScope.launch {
                        modalBottomSheetState.show()
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
                                    closeBottomSheet() //TODO 저장하기 로직 구현 해야함
                                },
                                BottomSheetItemUiModel("친구에게 질문 보내기", R.drawable.ic_send) {},
                                BottomSheetItemUiModel("취소", R.drawable.ic_x) {
                                    closeBottomSheet()
                                },
                            )
                        )
                    },
                    sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ) {
                    ReplyPreferenceScreen(
                        questions = list,
                        onConfirmButtonClick = {
                            showBottomSheet()
                        }
                    ) {
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun ReplyPreferenceScreen(
    questions: List<QuestionUiModel> = listOf(),
    userName: String = "영은",
    onConfirmButtonClick: () -> Unit = {},
    onBackButtonClick: () -> Unit = {},
) {
    Column {
        ChinChinToolbar(
            title = "취향 질문 답변하기",
            isActiveConfirmButton = true, //TODO 질문 다 답변했으면 true해야함
            isAbleConfirmButton = true,
            onConfirmButtonClick = onConfirmButtonClick
        ) {
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
