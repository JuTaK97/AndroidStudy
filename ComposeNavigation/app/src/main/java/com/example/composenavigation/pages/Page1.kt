package com.example.composenavigation.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.GroupCheckBox
import com.example.composenavigation.ShowStack

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Page1(navController: NavController) {
    val destinationList = listOf("page1", "page2", "page3", "page4", "main")
    val optionList = listOf("singleTop", "restoreState", "popUpTo")
    val popUpToOptionList = listOf("inclusive", "saveState")

    val destinationCheckBoxStates =
        remember { mutableStateListOf(false, false, false, false, false) }
    val optionCheckBoxStates =
        remember { mutableStateListOf(false, false, false) }
    val popUpToOptionBoxStates =
        remember { mutableStateListOf(false, false) }

    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
        Text(text = "Here is PAGE 1", fontWeight = FontWeight.Bold, fontSize = 25.sp)
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

        var chosenPopupTo by remember { mutableStateOf("") }
        if (optionCheckBoxStates[2]) {
            var dropDownExpanded by remember { mutableStateOf(false) }

            Column(Modifier.padding(start = 15.dp)) {
                ExposedDropdownMenuBox(
                    expanded = dropDownExpanded,
                    onExpandedChange = { dropDownExpanded = dropDownExpanded.not() }
                ) {
                    TextField(
                        readOnly = true,
                        value = "popUpTo: $chosenPopupTo",
                        onValueChange = {},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropDownExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = dropDownExpanded,
                        onDismissRequest = {
                            dropDownExpanded = false
                        }
                    ) {
                        navController.backQueue
                            .filter {
                                it.destination.route != null
                            }
                            .map {
                                it.destination.route!!
                            }
                            .distinct()
                            .forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        chosenPopupTo = it
                                        dropDownExpanded = false
                                    }
                                ) {
                                    Text(it)
                                }
                            }
                    }
                }
                GroupCheckBox(
                    list = popUpToOptionList,
                    states = popUpToOptionBoxStates
                )
            }
        }
        else {
            chosenPopupTo = ""
        }

        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                destinationCheckBoxStates.forEachIndexed { index, state ->
                    if (state) navController.navigate(destinationList[index]) {
                        launchSingleTop = optionCheckBoxStates[0]
                        restoreState = optionCheckBoxStates[1]
                        if (chosenPopupTo.isEmpty().not()) {
                            popUpTo(chosenPopupTo) {
                                inclusive = popUpToOptionBoxStates[0]
                                saveState = popUpToOptionBoxStates[1]
                            }
                        }
                    }
                }
            }
        ) {
            Text("Navigate!", fontSize = 25.sp)
        }
        ShowStack(navController)
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
fun Page1Preview() {
    Page1(rememberNavController())
}