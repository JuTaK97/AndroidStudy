package com.example.composenavigation.rounds

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composenavigation.ShowStack

@Composable
fun Round1(navController: NavController) {
    Column {
        Text(text = "Here is Round 1", fontSize = 30.sp)
        Button(onClick = { navController.navigate("round2") }) {
            Text(text = "Go to Round 2", fontSize = 25.sp)
        }

        ShowStack(navController)
    }
}