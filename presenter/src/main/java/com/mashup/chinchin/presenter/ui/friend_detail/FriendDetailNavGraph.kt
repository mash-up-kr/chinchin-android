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
    answersFromFriend: List<QuestionUiModel>,
    expectedAnswers: List<QuestionUiModel>,
    isSavedTempQuestions: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = FriendDetailNavScreen.ANSWER_FROM_FRIEND.route
    ) {
        composable(route = FriendDetailNavScreen.ANSWER_FROM_FRIEND.route) {
            AnswerFromFriendScreen(answersFromFriend)
        }
        composable(route = FriendDetailNavScreen.ANSWER_EXPECTED.route) {
            AnswersExpectedScreen(expectedAnswers, isSavedTempQuestions)
        }
    }
}
