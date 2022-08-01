package com.mashup.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.presenter.main.HomeScreen
import com.mashup.presenter.main.MoreScreen
import com.mashup.presenter.main.RecommendFriendsScreen
import com.mashup.presenter.main.model.RecommendFriendUiModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
    recommendFriends: List<RecommendFriendUiModel>,
    showBottomSheet: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = MainNavScreen.Home.route,
    ) {
        composable(route = MainNavScreen.Home.route) {
            HomeScreen()
        }
        composable(route = MainNavScreen.RecommendFriends.route) {
            RecommendFriendsScreen(recommendFriends, showBottomSheet)
        }
        composable(route = MainNavScreen.More.route) {
            MoreScreen()
        }
    }
}
