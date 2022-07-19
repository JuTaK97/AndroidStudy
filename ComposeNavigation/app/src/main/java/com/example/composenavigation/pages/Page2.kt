package com.example.composenavigation.pages

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.GroupCheckBox
import com.example.composenavigation.ShowStack

@Composable
fun Page2(navController: NavController) {
    val destinationList = listOf("page1", "page2", "page3", "page4", "main")
    val optionList = listOf("singleTop", "restoreState", "popUpTo")

    val destinationCheckBoxStates = remember { mutableStateListOf(false, false, false, false, false) }
    val optionCheckBoxStates = remember { mutableStateListOf(false, false, false) }

    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
        Text(text = "Here is PAGE 2", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Choose Destination", fontSize = 18.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(5.dp))
        GroupCheckBox(
            list = destinationList,
            states = destinationCheckBoxStates,
            onlyOneOption = true
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Choose Options", fontSize = 18.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(5.dp))
        GroupCheckBox(
            list = optionList,
            states = optionCheckBoxStates
        )

        Button(
            onClick = {
                destinationCheckBoxStates.forEachIndexed { index, state ->
                    if(state) navController.navigate(destinationList[index]) {
                        launchSingleTop = optionCheckBoxStates[0]
                        restoreState = optionCheckBoxStates[1]
                    }
                }
            }
        ) {
            Text("Navigate!", fontSize = 30.sp)
        }
        ShowStack(navController)
    }
}