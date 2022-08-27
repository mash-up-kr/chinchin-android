package com.mashup.chinchin.presenter.ui.friend_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.chinchin.presenter.friend_detail.AnswerFromFriendScreen
import com.mashup.chinchin.presenter.friend_detail.AnswersExpectedScreen
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

@Composable
fun FriendDetailNavGraph(
    navController: NavHostController,
    friendAnswers: List<QuestionUiModel>,
    myAnswers: List<QuestionUiModel>,
    isSavedTempQuestions: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = FriendDetailNavScreen.FRIEND_ANSWER.route
    ) {
        composable(route = FriendDetailNavScreen.FRIEND_ANSWER.route) {
            AnswerFromFriendScreen(friendAnswers)
        }
        composable(route = FriendDetailNavScreen.MY_ANSWER.route) {
            AnswersExpectedScreen(myAnswers, isSavedTempQuestions)
        }
    }
}
