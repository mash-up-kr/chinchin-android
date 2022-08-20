package com.mashup.chinchin.presenter.reply_preference

import android.content.Intent
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.send_preference.SendPreferenceCompleteActivity
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.ImageDialog
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.BottomSheetContent
import com.mashup.chinchin.presenter.ui.common.bottom_sheet.model.BottomSheetItemUiModel
import com.mashup.chinchin.presenter.ui.reply_preference.ReplyPreferenceQuestionList
import com.mashup.chinchin.presenter.ui.reply_preference.ReplyPreferenceTitle
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.Gray_600
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
                ReplyPreferenceScreen(list) {
                    finish()
                }
            }
        }
    }
}

@Composable
fun ReplyPreferenceScreen(
    questions: List<QuestionUiModel> = listOf(),
    userName: String = "영은",
    onBackButtonClick: () -> Unit = {},
) {
    val context = LocalContext.current
    val (showSendDialog, setShowSendDialog) = remember { mutableStateOf(false) }
    val (showCancelDialog, setShowCancelDialog) = remember { mutableStateOf(false) }

    Column {
        ChinChinToolbar(
            title = "취향 질문 답변하기",
            isActiveConfirmButton = true, //TODO 질문 다 답변했으면 true해야함
            isAbleConfirmButton = true,
            onConfirmButtonClick = { setShowSendDialog(true) },
            confirmDrawableId = R.drawable.ic_send
        ) {
            setShowCancelDialog(true)
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
        if (showSendDialog) {
            ImageDialog(
                titleText = "취향 질문지를 \n" +
                        "친구에게 전송할까요?",
                confirmText = "네, 전송할래요",
                cancelText = "아니오",
                subTitleText = "보낸 취향 질문지 답변은 수정이 불가능해요",
                onClickConfirm = {
                    setShowSendDialog(false)
                    val intent = Intent(context, SendPreferenceCompleteActivity::class.java)
                    context.startActivity(intent)
                }) {
                setShowSendDialog(false)
            }
        }
        if (showCancelDialog) {
            ImageDialog(
                titleText = "답변하지 않고 페이지를 \n나가시겠어요?",
                confirmText = "나가기",
                cancelText = "닫기",
                subTitleText = "작성중인 답변은 저장되지 않아요",
                onClickConfirm = {
                    setShowCancelDialog(false)
                    onBackButtonClick()
                }) {
                setShowCancelDialog(false)
            }
        }
    }

}
