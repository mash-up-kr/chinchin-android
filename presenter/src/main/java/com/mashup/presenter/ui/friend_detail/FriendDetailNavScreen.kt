package com.mashup.presenter.ui.friend_detail

enum class FriendDetailNavScreen(
    val route: String,
    val title: String,
) {
    AnswerFromFriend(
        route = "answerFromFriend",
        title = "친구 대답"
    ),
    ExpectedAnswer(
        route = "expectedAnswer",
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