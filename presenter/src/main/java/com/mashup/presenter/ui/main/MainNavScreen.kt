package com.mashup.presenter.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainNavScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    Home(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    ),
    RecommendFriends(
        route = "recommendFriends",
        title = "RecommendFriends",
        icon = Icons.Default.Person
    ),
    More(
        route = "more",
        title = "More",
        icon = Icons.Default.Settings
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
