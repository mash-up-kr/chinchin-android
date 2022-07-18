package com.mashup.presenter.friend_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.presenter.friend_detail.model.FriendProfileUiModel
import com.mashup.presenter.friend_detail.model.QuestionUiModel
import com.mashup.presenter.ui.common.ChinChinToolbar
import com.mashup.presenter.ui.friend_detail.*
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.ChinchinTheme
import com.mashup.presenter.ui.theme.White

class FriendDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                FriendDetailScreen(
                    screens = initScreen(),
                    friendProfileUiModel = initFriendProfile(),
                    answersFromFriend = initQuestionList(),
                    expectedAnswers = initQuestionList(),
                ) {
                    finish()
                }
            }
        }
    }

    companion object {
        fun initScreen(): List<FriendDetailNavScreen> {
            return listOf(
                FriendDetailNavScreen.AnswerFromFriend,
                FriendDetailNavScreen.ExpectedAnswer,
            )
        }

        fun initFriendProfile(): FriendProfileUiModel {
            return FriendProfileUiModel(
                profileUrl = "https://s3-alpha-sig.figma.com/img/8bbf/4576/60f7ab03c8f19e4de6c019fd6f0769a2?Expires=1658102400&Signature=RxL8HYAoquqxPWHDVN-0nIXbJUGIoAa1QW9KqeL0-2uZMM0iHdVrk9d~4LH2fGvEg4gNl7-VBcWNFe446Xz7bX7e8-qh5-IKzxUoVmjMXQOlMhz8bjepncmfJO0PoyAuVmWOqZaSvbIw1rAG-xP3vDmHXIII~gyG4c8rk5Nf3D8PL4vGEvgI-L73hoAjI4rJDbLnciRj2n52nOu67BAhSytQso9G2V0cytGDhfKEOR-FdexnaI3KWTb1uLGzJb1STkOjJNXVqp9eOegL5KfF3fUpu4uxdXe0EvbWg6ij5nTQlhy1qtRlKe4o1liApZ5USODd0eODBZEunGs9szyfTw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA",
                name = "매쉬업",
                birthday = "12월 31일",
                groupName = "매쉬업 그룹"
            )
        }

        fun initQuestionList(): List<QuestionUiModel> {
            val questionUiModels = mutableListOf<QuestionUiModel>()
            repeat(20) {
                questionUiModels.add(
                    QuestionUiModel(
                        question = "좋아하는 음식은 무엇입니까?",
                        answer = "난 곱창이 세상에서 제일 좋아"
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
        answersFromFriend = FriendDetailActivity.initQuestionList(),
        expectedAnswers = FriendDetailActivity.initQuestionList(),
    )
}

@Composable
fun FriendDetailScreen(
    screens: List<FriendDetailNavScreen> = FriendDetailNavScreen.values().toList(),
    friendProfileUiModel: FriendProfileUiModel,
    answersFromFriend: List<QuestionUiModel>,
    expectedAnswers: List<QuestionUiModel>,
    finishActivity: () -> Unit = {}
) {
    val naveController = rememberNavController()
    val navBackStackEntry by naveController.currentBackStackEntryAsState()
    val currentDestination = FriendDetailNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    Column {
        ChinChinToolbar(title = "") {
            finishActivity()
        }
        Row(
            modifier = Modifier
                .height(142.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 15.dp),
        ) {
            FriendProfile(friendProfileUiModel)
        }

        Scaffold(
            topBar = {
                FriendDetailNavBar(
                    screens = screens,
                    currentDestination = currentDestination,
                ) { screen ->
                    naveController.navigate(screen.route) {
                        popUpTo(naveController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }
        ) {
            Column() {
                QuestionSizeText(2)
                FriendDetailNavGraph(
                    navController = naveController,
                    answersFromFriend = answersFromFriend,
                    expectedAnswers = expectedAnswers,
                )
            }

        }
    }

}
