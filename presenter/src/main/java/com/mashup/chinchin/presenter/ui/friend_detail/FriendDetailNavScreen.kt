package com.mashup.chinchin.presenter.ui.friend_detail

enum class FriendDetailNavScreen(
    val route: String,
    val title: String,
) {
    ANSWER_FROM_FRIEND(
        route = "answerFromFriend",
        title = "친구 대답"
    ),
    ANSWER_EXPECTED(
        route = "expectedAnswer",
        title = "내가 쓴 답"
    );

    companion object {
        fun fromRoute(route: String?): FriendDetailNavScreen =
            when (route?.substringBefore("/")) {
                ANSWER_FROM_FRIEND.route -> ANSWER_FROM_FRIEND
                ANSWER_EXPECTED.route -> ANSWER_EXPECTED
                null -> ANSWER_FROM_FRIEND
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}
