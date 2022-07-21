package com.example.composenavigation.books

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class BookItem(
    val bookName: String,
    val bookWriter: String,
    val content: String = ""
)

@Composable
fun Library(navController: NavController) {

    val bookList = remember {
        mutableStateListOf(
            BookItem("book1", "writer1"),
            BookItem("book2", "writer2"),
            BookItem("book3", "writer3"),
            BookItem("book4", "writer4")
        )
    }

    Column {
        Text("Welcome to Library", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))
        BookGrid(books = bookList, navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookGrid(
    books: MutableList<BookItem>,
    navController: NavController
) {
    LazyVerticalGrid(cells = GridCells.Adaptive(96.dp), contentPadding = PaddingValues(5.dp)) {
        items(books.size) { index ->
            Card(
                backgroundColor = Color.Cyan,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(96.dp)
                    .clickable {
                        val bookName = books[index].bookName
                        navController.navigate("book?bookName=${bookName}&bookPage={1}")
                    },
                elevation = 8.dp
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = books[index].bookName,
                        fontSize = 25.sp
                    )
                    Text(
                        text = "by " + books[index].bookWriter,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))

    var newBookName by remember { mutableStateOf("") }
    var newBookAuthor by remember { mutableStateOf("") }
    Row {
        Text("NEW BOOK TITLE : ", fontSize = 20.sp)
        TextField(value = newBookName, onValueChange = { newBookName = it })
    }
    Row {
        Text("NEW BOOK AUTHOR : ", fontSize = 20.sp)
        TextField(value = newBookAuthor, onValueChange = { newBookAuthor = it })
    }
    Button(onClick = { navController.navigate("book?bookName=$newBookName") }) {
        Text("Add a book : $newBookName")
    }

    Button(onClick = { navController.navigate("round") }) {
        Text("round")
    }
}

@Composable
fun Book(navController: NavController, bookName: String?, bookPage: Int?) {
    Column {
        Text(bookName ?: "null", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text( "${bookPage?: 1}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))

        var textField by remember {
            mutableStateOf("")
        }
        Text("Write the name of a book to navigate")
        TextField(
            value = textField,
            onValueChange = {
                textField = it
            }
        )
        Button(onClick = { navController.navigate("book?bookName=${textField}&bookPage={35}") }
        ) {
            Text("Navigate to book name : $textField")
        }
    }
}