package com.mashup.chinchin.presenter.friend_detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.chinchin.presenter.common.ChinChinAnswerCardState
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.friend_detail.model.FriendProfileUiModel
import com.mashup.chinchin.presenter.friend_information.FriendInformationActivity
import com.mashup.chinchin.presenter.friend_information.FriendInformationActivity.Companion.EXTRA_FRIEND
import com.mashup.chinchin.presenter.friend_information.FriendInformationActivity.Companion.EXTRA_PROFILE_TYPE
import com.mashup.chinchin.presenter.friend_information.model.FriendProfileType
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity.Companion.EXTRA_FRIEND_NAME
import com.mashup.chinchin.presenter.ui.common.ChinChinToolbar
import com.mashup.chinchin.presenter.ui.common.StatusBarColor
import com.mashup.chinchin.presenter.ui.friend_detail.*
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class FriendDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ChinchinTheme {
                FriendDetailScreen {
                    finish()
                }
            }
        }
    }

    companion object {
        const val EXTRA_FRIEND_ID = "EXTRA_FRIEND_ID"
    }
}

@Preview(showBackground = true)
@Composable
fun FriendDetailPreview() {
    FriendDetailScreen()
}

@Composable
fun FriendDetailScreen(
    isSavedTempQuestions: Boolean = false,
    finishActivity: () -> Unit = {},
) {
    // basic data
    val context = LocalContext.current
    val viewModel: FriendDetailViewModel = hiltViewModel()

    // nav data
    val naveController = rememberNavController()
    val navBackStackEntry by naveController.currentBackStackEntryAsState()
    val currentDestination = FriendDetailNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    // viewModel data
    val friendProfile: FriendProfileUiModel? =
        viewModel.friendProfile.observeAsState().value

    // screen data
    val screens = listOf(
        FriendDetailNavScreen.FRIEND_ANSWER,
        FriendDetailNavScreen.MY_ANSWER,
    )

    // toolbar data
    val toolbarHeight = 263.dp  //TODO 임시로 263고정함 추후 하위 컴포저블 사이즈 측정해서 동적으로 변하게 수정 해야함
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
                friendAnswers = friendProfile?.friendAnswers ?: emptyList(),
                myAnswers = friendProfile?.myAnswers ?: emptyList(),
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
                    val intent = Intent(context, FriendInformationActivity::class.java).apply {
                        putExtra(EXTRA_PROFILE_TYPE, FriendProfileType.UPDATE)
                        friendProfile?.profile?.let {
                            putExtra(EXTRA_FRIEND, it.toFriendUiModel())
                        }
                    }
                    finishActivity()
                    context.startActivity(intent)
                },
                onButtonClick = {
                    val intent = Intent(context, SendPreferenceActivity::class.java).apply {
                        friendProfile?.let {
                            putExtra(EXTRA_FRIEND_ID, it.profile.id)
                            putExtra(EXTRA_FRIEND_NAME, it.profile.name)
                        }
                    }
                    context.startActivity(intent)
                },
                profileUiModel = friendProfile?.profile
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
            QuestionSizeText(friendProfile?.friendAnswers?.size ?: 0)
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

