package com.example.jetpackcomposepractice

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val a = remember { mutableStateOf(0) }
            var b by remember { mutableStateOf(0) }
            val (c, d) = remember { mutableStateOf("") }
            var e by remember { mutableStateOf("") }

            JetpackComposePracticeTheme {
                CanvasDraw()

//                MyApp()


//                Column {
//                    ClickCounter(clicks = b, onClick = { b += 1 })
//                    ClickCounter(clicks = a.value, onClick = { a.value = a.value + 1 })
//                    TextField(value = c, onValueChange = d)
//                    Text(text = c)
//                    TextField(value = e, onValueChange = { change -> e = change })
//                    Text(text = e)
//
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Row {
//                        Text(text = "1", modifier = Modifier.clickable {
//                            a.value = 1
//                            b = 1
//                        })
//                        Text(text = "2", modifier = Modifier.clickable {
//                            a.value = 2
//                            b = 2
//                        })
//                        Text(text = "3", modifier = Modifier.clickable {
//                            a.value = 3
//                            b = 3
//                        })
//                        Text(text = "4", modifier = Modifier.clickable {
//                            a.value = 4
//                            b = 4
//                        })
//                    }
//                }
            }
        }
    }
}

@Composable
fun CanvasDraw() {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        drawLine(
            start = Offset(30F, 30F),
            end = Offset(150F, 150F),
            color = Blue
        )
    })
}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

@Composable
fun MyApp(names: List<String> = List(1000) { "$it" }) {
//    Column(modifier = Modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) OnboardingScreen { shouldShowOnboarding = false }
    else Greetings(names)
}

@Composable
private fun Greeting(name: String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 12.dp)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
                if (isExpanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4)
                    )
                }
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
//            OutlinedButton(onClick = { isExpanded = isExpanded.not() }) {
//                if (isExpanded) Text(stringResource(R.string.show_less))
//                else Text("Show more")
//            }
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.clickable { isExpanded = isExpanded.not() }) {
        Text(text = msg.author, maxLines = if (isExpanded) Int.MAX_VALUE else 1)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = msg.body, maxLines = if (isExpanded) Int.MAX_VALUE else 1)
    }
}

@Preview(
    showBackground = true,
    widthDp = 480,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewNightMode"
)
@Preview(showBackground = true, widthDp = 480)
@Composable
fun DefaultPreview() {
    JetpackComposePracticeTheme {
//        val words = listOf("World", "Compose")
//        Column(modifier = Modifier.padding(vertical = 10.dp)) {
//            for (word in words) {
//                Surface(color = MaterialTheme.colors.primary,
//                modifier = Modifier.padding(5.dp)) {
//                    Text(
//                        text = "Hello, $word",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(20.dp)
//                    )
//                }
//            }
//        }
        MyApp()
    }
}

data class Message(val author: String, val body: String)

object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Colleague",
            "Test...Test...Test..."
        ),
        Message(
            "Colleague",
            "List of Android versions:\n" +
                    "Android KitKat (API 19)\n" +
                    "Android Lollipop (API 21)\n" +
                    "Android Marshmallow (API 23)\n" +
                    "Android Nougat (API 24)\n" +
                    "Android Oreo (API 26)\n" +
                    "Android Pie (API 28)\n" +
                    "Android 10 (API 29)\n" +
                    "Android 11 (API 30)\n" +
                    "Android 12 (API 31)\n"
        ),
        Message(
            "Colleague",
            "I think Kotlin is my favorite programming language.\n" +
                    "It's so much fun!"
        ),
        Message(
            "Colleague",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Colleague",
            "Hey, take a look at Jetpack Compose, it's great!\n" +
                    "It's the Android's modern toolkit for building native UI." +
                    "It simplifies and accelerates UI development on Android." +
                    "Less code, powerful tools, and intuitive Kotlin APIs :)"
        ),
        Message(
            "Colleague",
            "It's available from API 21+ :)"
        ),
        Message(
            "Colleague",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Colleague",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Colleague",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Colleague",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Colleague",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Colleague",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Colleague",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}

@Composable
private fun Greetings(names: List<String> = listOf("World", "Compose")) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 480, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackComposePracticeTheme() {
        OnboardingScreen {}
    }
}