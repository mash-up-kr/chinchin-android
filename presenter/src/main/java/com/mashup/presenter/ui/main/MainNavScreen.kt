package com.mashup.presenter.ui.main

import com.mashup.presenter.R

enum class MainNavScreen(
    val route: String,
    val iconResId: Int,
) {
    Home(
        route = "home",
        iconResId = R.drawable.icon_home
    ),
    RecommendFriends(
        route = "recommendFriends",
        iconResId = R.drawable.icon_search
    ),
    More(
        route = "more",
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
