package com.mashup.chinchin.presenter.ui.friend_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.chinchin.presenter.ui.theme.Black
import com.mashup.chinchin.presenter.ui.theme.Gray_400
import com.mashup.chinchin.presenter.ui.theme.White

@Composable
fun FriendDetailNavBar(
    screens: List<FriendDetailNavScreen>,
    currentDestination: FriendDetailNavScreen,
    onTabSelected: (FriendDetailNavScreen) -> Unit,
) {
    //TODO 선택된 아이템 하단 줄 표시
    BottomNavigation(
        backgroundColor = White, elevation = 0.dp
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                onTabSelected = {
                    onTabSelected(screen)
                }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: FriendDetailNavScreen,
    currentDestination: FriendDetailNavScreen,
    onTabSelected: () -> Unit,
) {
    val isCurrentDestination = (currentDestination == screen)
    BottomNavigationItem(
        icon = {
            NavBar(screen.title, isCurrentDestination)
        },
        selectedContentColor = Black,
        unselectedContentColor = Gray_400,
        selected = isCurrentDestination,
        onClick = { onTabSelected() },
    )
}

@Composable
fun NavBar(iconTitle: String, isCurrentDestination: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = iconTitle,
        )
        if (isCurrentDestination) {
            Divider(
                color = Black,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(6.dp)
            )
        }
    }
}