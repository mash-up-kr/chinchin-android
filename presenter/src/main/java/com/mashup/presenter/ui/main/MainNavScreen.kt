package com.mashup.presenter.ui.main

import com.mashup.presenter.R

enum class MainNavScreen(
    val route: String,
    val title: String,
    val iconResId: Int,
) {
    Home(
        route = "home",
        title = "Home",
        iconResId = R.drawable.icon_home
    ),
    RecommendFriends(
        route = "recommendFriends",
        title = "RecommendFriends",
        iconResId = R.drawable.icon_search
    ),
    More(
        route = "more",
        title = "More",
        iconResId = R.drawable.icon_more
    );

    companion object {
        fun fromRoute(route: String?): MainNavScreen =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                RecommendFriends.route -> RecommendFriends
                More.route -> More
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
