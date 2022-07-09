package com.mashup.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.presenter.main.HomeScreen
import com.mashup.presenter.main.RecommendFriendsScreen
import com.mashup.presenter.main.MoreScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainNavScreen.Home.route,
    ) {
        composable(route = MainNavScreen.Home.route) {
            HomeScreen()
        }
        composable(route = MainNavScreen.RecommendFriends.route) {
            RecommendFriendsScreen()
        }
        composable(route = MainNavScreen.More.route) {
            MoreScreen()
        }
    }
}
