package com.mashup.chinchin.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.chinchin.presenter.main.HomeScreen
import com.mashup.chinchin.presenter.main.RecommendFriendsScreen
import com.mashup.chinchin.presenter.main.MoreScreen
import com.mashup.chinchin.presenter.main.model.RecommendFriendUiModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    recommendFriends: List<RecommendFriendUiModel>,
) {
    NavHost(
        navController = navController,
        startDestination = MainNavScreen.Home.route,
    ) {
        composable(route = MainNavScreen.Home.route) {
            HomeScreen()
        }
        composable(route = MainNavScreen.RecommendFriends.route) {
            RecommendFriendsScreen(recommendFriends)
        }
        composable(route = MainNavScreen.More.route) {
            MoreScreen()
        }
    }
}
