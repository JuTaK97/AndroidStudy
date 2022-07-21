package com.example.composenavigation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.composenavigation.ShowStack

@Composable
fun SettingPage(navController: NavController) {
    Column() {
        Text(text = "Settings")

        ShowStack(navController)
    }
}