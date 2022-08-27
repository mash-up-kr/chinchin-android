package com.mashup.chinchin.presenter.ui.friend_detail

enum class FriendDetailNavScreen(
    val route: String,
    val title: String,
) {
    FRIEND_ANSWER(
        route = "answerFromFriend",
        title = "친구 대답"
    ),
    MY_ANSWER(
        route = "expectedAnswer",
        title = "내가 쓴 답"
    );

    companion object {
        fun fromRoute(route: String?): FriendDetailNavScreen =
            when (route?.substringBefore("/")) {
                FRIEND_ANSWER.route -> FRIEND_ANSWER
                MY_ANSWER.route -> MY_ANSWER
                null -> FRIEND_ANSWER
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}
