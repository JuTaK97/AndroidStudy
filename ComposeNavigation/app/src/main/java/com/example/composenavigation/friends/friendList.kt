package com.example.composenavigation.friends

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.composenavigation.ShowStack

@Composable
fun FriendList(navController: NavController) {
    Text(text = "friendList")

    ShowStack(navController)
}