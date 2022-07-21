package com.example.composenavigation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.composenavigation.MainPage
import com.example.composenavigation.books.Book
import com.example.composenavigation.books.Library
import com.example.composenavigation.pages.Page
import com.example.composenavigation.rounds.Round1
import com.example.composenavigation.rounds.Round2
import com.example.composenavigation.rounds.Round3

fun NavGraphBuilder.roundRound(navController: NavController) {
    navigation(startDestination = "round1", route = "round") {
        composable("round1") { Round1(navController) }
        composable("round2") { Round2(navController) }
        composable("round3") { Round3(navController) }
    }
}

fun NavGraphBuilder.main(navController: NavController) {
    navigation(startDestination = "main", route = "home") {
        composable("main") { MainPage(navController) }

        composable("page1") { Page(navController, 1) }
        composable("page2") { Page(navController, 2) }
        composable("page3") { Page(navController, 3) }
        composable("page4") { Page(navController, 4) }

        composable("library") { Library(navController) }
        composable(
            route = "book?bookName={bookName}&bookPage={bookPage}",
            arguments = listOf(
                navArgument("bookName") {
                    type = NavType.StringType
                    defaultValue = "default"
                },
                navArgument("bookPage") {
                    type = NavType.IntType
                    defaultValue = 39
                },
            )
        ) { backStackEntry ->
            val bookName = backStackEntry.arguments?.getString("bookName")
            val bookPage = backStackEntry.arguments?.getInt("bookPage")
            Book(navController, bookName = bookName, bookPage = bookPage)
        }
        roundRound(navController)
    }


}
