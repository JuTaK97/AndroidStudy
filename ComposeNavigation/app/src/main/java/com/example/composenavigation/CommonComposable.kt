package com.example.composenavigation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GroupCheckBox(
    list: List<String> = emptyList(),
    states: MutableList<Boolean> = mutableListOf(),
    onlyOneOption: Boolean = false
) {
    Column {
        list.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .toggleable(
                        value = states[index],
                        role = Role.Checkbox,
                        onValueChange = {
                            states[index] = states[index].not()
                            if (onlyOneOption && states[index]) {
                                repeat(list.size) {
                                    if (it != index) states[it] = false
                                }
                            }
                        },
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    )
                    .padding(5.dp)
            ) {
                Checkbox(
                    checked = states[index],
                    onCheckedChange = null
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item)
            }
        }
    }
}

@Composable
fun ShowStack(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f, true))
        Divider(thickness = 2.dp)
        Text("NavController Stack", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            Modifier
                .padding(5.dp)
                .horizontalScroll(scrollState), verticalAlignment = Alignment.CenterVertically) {
            navController.backQueue
                .filter {
                    it.destination.route != null
                }
                .forEach {
                    Text(text = it.destination.route!!, fontSize = 18.sp)
                    Text(" -> ")
                }
        }
    }
}