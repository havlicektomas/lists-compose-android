
package com.raywenderlich.android.jetpackcompose.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.android.jetpackcompose.R
import com.raywenderlich.android.jetpackcompose.router.BackButtonHandler
import com.raywenderlich.android.jetpackcompose.router.JetFundamentalsRouter
import com.raywenderlich.android.jetpackcompose.router.Screen

private val bookCategories = listOf(
    BookCategory(
        R.string.android,
        listOf(
            R.drawable.android_aprentice,
            R.drawable.saving_data_android,
            R.drawable.advanced_architecture_android
        )
    ),
    BookCategory(
        R.string.kotlin,
        listOf(
            R.drawable.kotlin_coroutines,
            R.drawable.kotlin_aprentice
        )
    ),
    BookCategory(
        R.string.swift,
        listOf(
            R.drawable.combine,
            R.drawable.rx_swift,
            R.drawable.swift_apprentice,
        )
    ),
    BookCategory(
        R.string.ios,
        listOf(
            R.drawable.core_data,
            R.drawable.ios_apprentice,
        )
    )
)

@Composable
fun ListScreen() {
  MyList()

  BackButtonHandler {
    JetFundamentalsRouter.navigateTo(Screen.Navigation)
  }
}

@Composable
fun MyList() {
  //TODO add your code here
    LazyColumn {
        items(items = bookCategories) { bookCategory ->
            ListItem(bookCategory = bookCategory)
        }
    }
}

@Composable
fun ListItem(bookCategory: BookCategory, modifier: Modifier = Modifier) {
  //TODO add your code here
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = stringResource(bookCategory.categoryResourceId),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.colorPrimary)
        )
        Spacer(modifier = modifier.height(8.dp))

        LazyRow {
            items(items = bookCategory.bookImageResources) { bookImage ->
                BookImage(imageResource = bookImage)
            }
        }
    }
}

@Composable
fun BookImage(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "book image",
        modifier = Modifier.size(170.dp, 200.dp),
        contentScale = ContentScale.Fit
    )
}

data class BookCategory(@StringRes val categoryResourceId: Int, val bookImageResources: List<Int>)