package com.mashup.chinchin.presenter.friend_detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity
import com.mashup.chinchin.presenter.add_friend.AddFriendActivity.Companion.EXTRA_PROFILE_TYPE
import com.mashup.chinchin.presenter.add_friend.model.FriendProfileType
import com.mashup.chinchin.presenter.common.ChinChinAnswerCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.friend_detail.model.FriendProfileUiModel
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.friend_detail.*
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.White
import kotlin.math.roundToInt

class FriendDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: ViewModel 만들면 이관
        // TODO: viewModel saveInstance 주입받아서 get 한다음에 init에서 api 호출
        val friendId = intent.extras?.get(EXTRA_FRIEND_ID) ?: return

        Log.e("friendId", friendId.toString())
        setContent {
            ChinchinTheme {
                FriendDetailScreen(
                    screens = initScreen(),
                    friendProfileUiModel = initFriendProfile(),
                    answersFromFriend = initFriendAnswerList(),
                    expectedAnswers = emptyList(),
                    isSavedTempQuestions = true,
                ) {
                    finish()
                }
            }
        }
    }

    companion object {
        const val EXTRA_FRIEND_ID = "EXTRA_FRIEND_ID"

        fun initScreen(): List<FriendDetailNavScreen> {
            return listOf(
                FriendDetailNavScreen.ANSWER_FROM_FRIEND,
                FriendDetailNavScreen.ANSWER_EXPECTED,
            )
        }

        fun initFriendProfile(): FriendProfileUiModel {
            return FriendProfileUiModel(
                profileUrl = "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                name = "매쉬업",
                birthday = "12월 31일",
                groupName = "매쉬업 그룹",
            )
        }

        fun initMyQuestionList(): List<QuestionUiModel> {
            val questionUiModels = mutableListOf<QuestionUiModel>()
            repeat(20) {
                questionUiModels.add(
                    QuestionUiModel(
                        question = "좋아하는 음식은 무엇입니까?",
                        answer = "난 곱창이 세상에서 제일 좋아",
                    )
                )
            }
            return questionUiModels
        }

        fun initFriendAnswerList(): List<QuestionUiModel> {
            val questionUiModels = mutableListOf<QuestionUiModel>()
            repeat(10) {
                questionUiModels.add(
                    QuestionUiModel(
                        question = "MBTI는 무엇인가요?",
                        answer = "ENFP!",
                    )
                )
            }
            return questionUiModels
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FriendDetailPreview() {
    FriendDetailScreen(
        screens = FriendDetailActivity.initScreen(),
        friendProfileUiModel = FriendDetailActivity.initFriendProfile(),
        answersFromFriend = FriendDetailActivity.initFriendAnswerList(),
        expectedAnswers = emptyList(),
        isSavedTempQuestions = true,
    )
}

@Composable
fun FriendDetailScreen(
    screens: List<FriendDetailNavScreen> = FriendDetailNavScreen.values().toList(),
    friendProfileUiModel: FriendProfileUiModel,
    answersFromFriend: List<QuestionUiModel>,
    expectedAnswers: List<QuestionUiModel>,
    isSavedTempQuestions: Boolean = false,
    finishActivity: () -> Unit = {},
) {
    val context = LocalContext.current
    val naveController = rememberNavController()
    val navBackStackEntry by naveController.currentBackStackEntryAsState()
    val currentDestination = FriendDetailNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    var toolbarHeight = 210.dp  //TODO 하위 컴포저블 사이즈 측정해서 동적으로 변하게 수정 해야함
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            //Todo 폴링 적용해야함 참고 https://github.com/onebone/compose-collapsing-toolbar.git
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    StatusBarColor()
    Box(
        Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Column {
            FriendDetailNavGraph(
                navController = naveController,
                answersFromFriend = answersFromFriend,
                expectedAnswers = expectedAnswers,
                isSavedTempQuestions = isSavedTempQuestions
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 56.dp)
                .offset {
                    IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt())
                }
                .background(White)
        ) {
            FriendProfile(
                onProfileClick = {
                    val intent = Intent(context, AddFriendActivity::class.java).apply {
                        putExtra(EXTRA_PROFILE_TYPE, FriendProfileType.MODIFY)
                    }
                    context.startActivity(intent)
                },
                onButtonClick = {
                    val intent = Intent(context, SendPreferenceActivity::class.java).apply {
                        // TODO: friend 보내기. 이름 필요.
                    }
                    context.startActivity(intent)
                },
                friendProfileUiModel = friendProfileUiModel
            )
            FriendDetailNavBar(
                screens = screens,
                currentDestination = currentDestination,
            ) { screen ->
                naveController.navigate(screen.route) {
                    popUpTo(naveController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
            QuestionSizeText(answersFromFriend.size)
        }
    }
    ChinChinToolbar(title = "") {
        finishActivity()
    }
}

@Composable
fun AnswerFromFriendScreen(
    answersFromFriend: List<QuestionUiModel>,
) {
    if (answersFromFriend.isEmpty()) {
        EmptyQuestionContent()
    } else {
        QuestionAnswerListContent(
            answers = answersFromFriend,
            cardState = ChinChinAnswerCardState.FRIEND_ANSWER
        )
    }
}

@Composable
fun AnswersExpectedScreen(
    answersExpected: List<QuestionUiModel>,
    isSavedTempQuestions: Boolean,
) {
    if (answersExpected.isEmpty()) {
        if (isSavedTempQuestions) {
            TempSavedQuestionContent()
        } else {
            EmptyQuestionContent()
        }
    } else {
        QuestionAnswerListContent(
            answers = answersExpected,
            cardState = ChinChinAnswerCardState.MY_ANSWER
        )
    }
}

