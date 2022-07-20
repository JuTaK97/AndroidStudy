package com.example.composenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composenavigation.books.Book
import com.example.composenavigation.books.Library
import com.example.composenavigation.databinding.ActivityRootBinding
import com.example.composenavigation.friends.FriendList
import com.example.composenavigation.pages.Page
import com.example.composenavigation.rounds.Round1
import com.example.composenavigation.rounds.Round2
import com.example.composenavigation.rounds.Round3

class RootActivity : AppCompatActivity() {

    lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
//            SetupUI()
            SetupBottomNavigation()
        }
    }

    @Composable
    fun SetupUI() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "main") {
            composable("main") { MainPage(navController) }

            composable("page1") { Page(navController, 1) }
            composable("page2") { Page(navController, 2) }
            composable("page3") { Page(navController, 3) }
            composable("page4") { Page(navController, 4) }

            composable("library") { Library(navController) }
            composable(
                route = "book?bookName={bookName}",
                arguments = listOf(navArgument("bookName") { defaultValue = "new book" })
            ) {
                Book(navController, it.arguments?.getString("bookName"))
            }
            roundRound(navController)

            // deep link
//                val uri = "https://www.example.com"
//                composable(
//                    "book?bookName={bookName}",
//                    deepLinks = listOf(navDeepLink {
//                        uriPattern = "$uri/{id}"
//                    })
//                ) {
//                    Book(navController = navController, bookName = it.arguments?.getString("bookName"))
//                }
        }
    }

    sealed class Screen(val route: String, @StringRes val resourceId: Int) {
        object HomePage : Screen("home", R.string.profile)
        object FriendsList : Screen("friendslist", R.string.friend_list)
    }
    val items = listOf(
        Screen.HomePage,
        Screen.FriendsList,
    )

    @Composable
    fun SetupBottomNavigation() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                            label = { Text(screen.route)},
                            selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(navController, startDestination = Screen.HomePage.route, Modifier.padding(innerPadding)) {
                main(navController)
//                composable(Screen.HomePage.route) { MainPage(navController)}
                composable(Screen.FriendsList.route) { FriendList(navController) }
            }
        }
    }

    private fun NavGraphBuilder.roundRound(navController: NavController) {
        navigation(startDestination = "round1", route = "round") {
            composable("round1") { Round1(navController) }
            composable("round2") { Round2(navController) }
            composable("round3") { Round3(navController) }
        }
    }

    private fun NavGraphBuilder.main(navController: NavController) {
        navigation(startDestination = "main", route = "home") {
            composable("main") { MainPage(navController) }

            composable("page1") { Page(navController, 1) }
            composable("page2") { Page(navController, 2) }
            composable("page3") { Page(navController, 3) }
            composable("page4") { Page(navController, 4) }

            composable("library") { Library(navController) }
            composable(
                route = "book?bookName={bookName}",
                arguments = listOf(navArgument("bookName") { defaultValue = "new book" })
            ) {
                Book(navController, it.arguments?.getString("bookName"))
            }
            roundRound(navController)
        }
    }
}

