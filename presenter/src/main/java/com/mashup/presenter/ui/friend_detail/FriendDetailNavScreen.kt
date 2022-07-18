package com.mashup.presenter.ui.friend_detail

import com.mashup.presenter.R

enum class FriendDetailNavScreen(
    val route: String,
    val iconResId: Int,
    val title: String,
) {
    AnswerFromFriend(
        route = "answerFromFriend",
        iconResId = R.drawable.ic_answer_from_friend,
        title = "친구 대답"
    ),
    ExpectedAnswer(
        route = "expectedAnswer",
        iconResId = R.drawable.ic_expected_answer,
        title = "내가 쓴 답"
    );

    companion object {
        fun fromRoute(route: String?): FriendDetailNavScreen =
            when (route?.substringBefore("/")) {
                AnswerFromFriend.route -> AnswerFromFriend
                ExpectedAnswer.route -> ExpectedAnswer
                null -> AnswerFromFriend
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}