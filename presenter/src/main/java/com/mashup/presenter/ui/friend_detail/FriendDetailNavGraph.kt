package com.mashup.presenter.ui.friend_detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.presenter.friend_detail.model.QuestionUiModel
import com.mashup.presenter.main.HomeScreen
import com.mashup.presenter.main.MoreScreen
import com.mashup.presenter.main.RecommendFriendsScreen
import com.mashup.presenter.main.model.RecommendFriendUiModel
import com.mashup.presenter.ui.main.MainNavScreen

@Composable
fun FriendDetailNavGraph(
    navController: NavHostController,
    answersFromFriend: List<QuestionUiModel>,
    expectedAnswers: List<QuestionUiModel>,

) {
    NavHost(
        navController = navController,
        startDestination = FriendDetailNavScreen.AnswerFromFriend.route
    ) {
        composable(route = FriendDetailNavScreen.AnswerFromFriend.route) {
            //AnswerFromFriendScreen()
            Spacer(modifier = Modifier.height(50.dp))
            Text("친구 대답")
        }
        composable(route = FriendDetailNavScreen.ExpectedAnswer.route) {
            //ExpectedAnswerScreen()
            Spacer(modifier = Modifier.height(50.dp))
            Text("내가 쓴 답")
        }
    }
}
