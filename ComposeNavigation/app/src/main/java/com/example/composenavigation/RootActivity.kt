package com.example.composenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.databinding.ActivityRootBinding
import com.example.composenavigation.pages.Page1
import com.example.composenavigation.pages.Page2
import com.example.composenavigation.pages.Page3
import com.example.composenavigation.pages.Page4

class RootActivity : AppCompatActivity() {

    lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainPage(navController) }

                composable("page1") { Page1(navController) }
                composable("page2") { Page2(navController) }
                composable("page3") { Page3(navController) }
                composable("page4") { Page4(navController) }
            }
        }
    }
}