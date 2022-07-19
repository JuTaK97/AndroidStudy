package com.example.composenavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.pages.Page1

@Composable
fun MainPage(navController: NavController) {

    Column(modifier = Modifier.padding(horizontal = 5.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = {
                navController.navigate("page1")
            }
        ) {
            Text("MOVE TO PAGE 1")
        }
        Button(onClick = { navController.navigate("page2") }) {
            Text("MOVE TO PAGE 2")
        }
        Button(onClick = { navController.navigate("page3") }) {
            Text("MOVE TO PAGE 3")
        }
        Button(onClick = { navController.navigate("page4") }) {
            Text("MOVE TO PAGE 4")
        }

        ShowStack(navController)
    }
}

@Preview
@Composable
fun MainPagePreview() {
    MainPage(rememberNavController())
}