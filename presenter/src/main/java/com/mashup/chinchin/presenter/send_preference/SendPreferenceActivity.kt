package com.mashup.chinchin.presenter.send_preference

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.CategoryUiModel
import com.mashup.chinchin.presenter.common.model.KeywordQuestionUiModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.edit_preference.EditPreferenceActivity
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity.Companion.EXTRA_BUNDLE
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity.Companion.EXTRA_QUESTIONS
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.ImageDialog
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.send_questions.QuestionCategoryList
import com.mashup.chinchin.presenter.ui.send_questions.SendPreferenceQuestionList
import com.mashup.chinchin.presenter.ui.send_questions.SendPreferenceQuestionTitle
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.Gray_600
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SendPreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                SendPreferenceScreen(
                    finish = { finish() }
                )
            }
        }
    }


    companion object{
        const val EXTRA_QUESTIONS = "EXTRA_QUESTIONS"
        const val EXTRA_BUNDLE = "EXTRA_BUNDLE"
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SendPreferenceScreen(
    finish: () -> Unit,
) {
    // basic data
    val context = LocalContext.current
    val viewModel: SendPreferenceViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    // viewModel data
    val categories = viewModel.getCategoryList()
    val isSendSuccess = viewModel.isSendSuccess.observeAsState().value

    // state data
    val (showSendDialog, setShowSendDialog) = remember { mutableStateOf(false) }
    val (showSaveDialog, setShowSaveDialog) = remember { mutableStateOf(false) }
    val (showCancelDialog, setShowCancelDialog) = remember { mutableStateOf(false) }

    // bottomsheet data
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

    if (isSendSuccess == true) {
        val intent = Intent(context, SendPreferenceCompleteActivity::class.java)
        context.startActivity(intent)
    } else {
        // TODO: Error Log
    }

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        closeBottomSheet()
    }

    StatusBarColor()
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
            categoryList = categories,
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
                viewModel.sendQuestionnaire()
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

@Composable
fun CreateQuestionSheetScreen(
    userName: String = "윤혜",
    onConfirmButtonClick: () -> Unit = {},
    categoryList: List<CategoryUiModel>,
    onBackButtonClick: () -> Unit = {},
) {
    //TODO 아래 주석 코드가 동작안됨 확인필요!! 도와주세요~
    //val questions by sendPreferenceViewModel._questionsLiveData.observeAsState()
    val sendPreferenceViewModel: SendPreferenceViewModel = viewModel()
    val questions = sendPreferenceViewModel.questions
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val passedExtras: Bundle? = result.data?.extras
                checkNotNull(passedExtras)
                val questions = passedExtras.getBundle(EXTRA_BUNDLE)
                    ?.getParcelableArrayList<QuestionUiModel>(EXTRA_QUESTIONS)
                questions?.let {
                    sendPreferenceViewModel.changeQuestions(questions.toList())
                }
            }
        }
    val context = LocalContext.current
    val questionsArrayList = ArrayList(questions.toList())

    val onClickEditButton: () -> Unit = {
        Intent(context, EditPreferenceActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelableArrayList(EXTRA_QUESTIONS, questionsArrayList)
            putExtra(EXTRA_BUNDLE, bundle)
        }.also {
            startForResult.launch(it)
        }
    }

    Column {
        ChinChinToolbar(
            title = "취향 질문 보내기",
            isActiveConfirmButton = true,
            isAbleConfirmButton = true, //TODO 활성화 기준 정해야함
            onConfirmButtonClick = onConfirmButtonClick
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
                sendPreferenceViewModel.addQuestion(it)
            }
            Spacer(modifier = Modifier.height(13.dp))

            SendPreferenceQuestionList(
                questions = questions,
                modifier = Modifier
                    .padding(top = 4.dp),
                onQuestionChanged = { index, questionText ->
                    sendPreferenceViewModel.changeQuestionByIndex(
                        index,
                        questionText
                    )
                },
                onAnswerChanged = { index, answerText ->
                    sendPreferenceViewModel.changeAnswerByIndex(
                        index,
                        answerText
                    )
                },
                onClickEditButton = onClickEditButton,
            )
        }
    }
}
