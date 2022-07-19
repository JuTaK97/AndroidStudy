package com.example.composenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.databinding.ActivityRootBinding
import com.example.composenavigation.pages.Page

class RootActivity : AppCompatActivity() {

    lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainPage(navController) }

                composable("page1") { Page(navController, 1) }
                composable("page2") { Page(navController, 2) }
                composable("page3") { Page(navController, 3) }
                composable("page4") { Page(navController, 4) }
            }
        }
    }
}