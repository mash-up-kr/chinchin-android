package com.mashup.chinchin.presenter.send_preference

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.CategoryUiModel
import com.mashup.chinchin.presenter.common.model.KeywordQuestionUiModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.ImageDialog
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
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
                val (showSendDialog, setShowSendDialog) = remember { mutableStateOf(false) }
                val (showSaveDialog, setShowSaveDialog) = remember { mutableStateOf(false) }
                val (showCancelDialog, setShowCancelDialog) = remember { mutableStateOf(false) }

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
                                BottomSheetItemUiModel("임시 저장하고 나가기", R.drawable.ic_exit) {
                                    closeBottomSheet()
                                    setShowSaveDialog(true)
                                },
                                BottomSheetItemUiModel("친구에게 질문 보내기", R.drawable.ic_send) {
                                    closeBottomSheet()
                                    setShowSendDialog(true)
                                },
                                BottomSheetItemUiModel("취소", R.drawable.ic_x) {
                                    closeBottomSheet()
                                    setShowCancelDialog(true)
                                },
                            )
                        )
                    },
                    sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                ) {
                    CreateQuestionSheetScreen(
                        categoryList = getCategoryList(),
                        onConfirmButtonClick = showBottomSheet
                    ) {
                        setShowCancelDialog(true)
                    }
                }
                if (showSendDialog) {
                    ImageDialog(
                        drawableId = R.drawable.img_congrats,
                        titleText = "취향 질문지를 \n" +
                                "친구에게 전송할까요?",
                        confirmText = "네, 전송할래요",
                        cancelText = "아니요",
                        subTitleText = "보낸 취향 질문은 수정이 불가능해요",
                        onClickConfirm = {
                            val intent = Intent(this, SendPreferenceCompleteActivity::class.java)
                            startActivity(intent)
                            setShowSendDialog(false)
                        },
                        onClickCancel = { setShowSendDialog(false) }
                    )
                }
                if (showSaveDialog) {
                    ImageDialog(
                        titleText = "작성한 취향 질문을 \n저장할까요?",
                        confirmText = "네, 저장할래요",
                        cancelText = "아니요",
                        subTitleText = "저장한 취향질문은 다시 작성할 수 있어요",
                        onClickConfirm = {
                            // TODO:  저장로직.
                            setShowSendDialog(false)
                            finish()
                        },
                        onClickCancel = {
                            setShowSaveDialog(false)
                        }
                    )
                }
                if (showCancelDialog) {
                    ImageDialog(
                        titleText = "작성하지 않고 페이지를 \n나가시겠어요?",
                        confirmText = "나가기",
                        cancelText = "닫기",
                        subTitleText = "상단 체크버튼을 눌러 질문을 저장해보세요",
                        onClickConfirm = {
                            setShowCancelDialog(false)
                            finish()
                        },
                        onClickCancel = {
                            setShowCancelDialog(false)
                        }
                    )
                }
            }
        }
    }

    private fun getCategoryList(): MutableList<CategoryUiModel> {
        val categoryList = mutableListOf<CategoryUiModel>()
        val preferences = CategoryUiModel(
            category = "취향 키워드",
            keywords = listOf(
                KeywordQuestionUiModel(keyword = "좋아하는 음식", question = "좋아하는 음식은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "싫어하는 음식", question = "싫어하는 음식은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 향", question = "좋아하는 향은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 옷 브랜드", question = "좋아하는 옷 브랜드는 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 꽃", question = "좋아하는 꽃은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 술", question = "좋아하는 술은 무엇인가요?"),
            )
        )

        val privateInformation = CategoryUiModel(
            category = "개인 정보",
            keywords = listOf(
                KeywordQuestionUiModel(keyword = "MBTI", question = "MBTI는 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "못 먹는 음식", question = "못 먹는 음식은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "옷 사이즈", question = "옷 사이즈는 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "신발 사이즈", question = "신발 사이즈는 어떻게 되나요?"),
                KeywordQuestionUiModel(keyword = "활동 지역", question = "주로 활동하는 지역은 어디인가요?"),
                KeywordQuestionUiModel(
                    keyword = "최애 영화",
                    question = "지금까지 봤던 영화 중 가장 좋았던 영화는 어떤거야?"
                ),
            )
        )
        categoryList.add(preferences)
        categoryList.add(privateInformation)

        return categoryList
    }
}

@Composable
fun CreateQuestionSheetScreen(
    userName: String = "윤혜",
    onConfirmButtonClick: () -> Unit = {},
    categoryList: List<CategoryUiModel>,
    onBackButtonClick: () -> Unit = {},
) {
    val questions = remember { mutableStateListOf<QuestionUiModel>() }

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
                text = "아래 카테고리들을 선택해 질문을 구성할 수 있습니다.",
                color = Gray_600,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
            QuestionCategoryList(categories = categoryList) {
                questions.add(it)
            }
            Spacer(modifier = Modifier.height(13.dp))

            SendPreferenceQuestionList(
                questions = questions,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}
