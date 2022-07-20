package com.example.composenavigation

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.ContextWrapper
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainPage(navController: NavController) {
    Column(
        modifier = Modifier.padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Here is Main Page", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(20.dp))
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
        Divider(thickness = 3.dp)

        Button(onClick = { navController.navigate("library") }) {
            Text("MOVE TO LIBRARY")
        }
        Divider(thickness = 3.dp)

        Button(onClick = { navController.navigate("round") }) {
            Text("MOVE TO ROUND")
        }
        Divider(thickness = 3.dp)

        // deep link
//        val name = "exampleName"
//        val context = LocalContext.current
//        val deepLinkIntent = Intent(
//            Intent.ACTION_VIEW,
//            "https://www.example.com/$name".toUri(),
//            context,
//            RootActivity::class.java
//        )
//        val deepLinkPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
//            addNextIntentWithParentStack(deepLinkIntent)
//            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//        Button(
//            onClick = {
//                val notificationBuilder = NotificationCompat.Builder(context, "test")
//                    .setSmallIcon(R.drawable.ic_launcher_background)
//                    .setContentTitle("hi")
//                    .setContentText("alarm")
//                    .setAutoCancel(true)
//                    .setContentIntent(deepLinkPendingIntent)
//                val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//                notificationManager.notify(0, notificationBuilder.build())
//            }
//        ) {
//            Text("DEEP LINK TEST")
//        }

        ShowStack(navController)
    }
}

@Preview
@Composable
fun MainPagePreview() {
    MainPage(rememberNavController())
}