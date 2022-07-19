package com.mashup.presenter.ui.friend_detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.presenter.friend_detail.AnswerFromFriendScreen
import com.mashup.presenter.friend_detail.AnswersExpectedScreen
import com.mashup.presenter.friend_detail.model.QuestionUiModel

@Composable
fun FriendDetailNavGraph(
    navController: NavHostController,
    answersFromFriend: List<QuestionUiModel>,
    expectedAnswers: List<QuestionUiModel>,
    isSavedTempQuestions: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = FriendDetailNavScreen.AnswerFromFriend.route
    ) {
        composable(route = FriendDetailNavScreen.AnswerFromFriend.route) {
            AnswerFromFriendScreen(answersFromFriend)
        }
        composable(route = FriendDetailNavScreen.ExpectedAnswer.route) {
            AnswersExpectedScreen(expectedAnswers, isSavedTempQuestions)
        }
    }
}
