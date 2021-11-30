package com.raywenderlich.android.jetpackcompose.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.raywenderlich.android.jetpackcompose.R
import com.raywenderlich.android.jetpackcompose.router.BackButtonHandler
import com.raywenderlich.android.jetpackcompose.router.JetFundamentalsRouter
import com.raywenderlich.android.jetpackcompose.router.Screen
import kotlin.math.ceil

private val items = listOf(
    Icons.Filled.Check,
    Icons.Filled.Close,
    Icons.Filled.ThumbUp,
    Icons.Filled.Build,
    Icons.Filled.Delete,
    Icons.Filled.Home,
    Icons.Filled.Close,
    Icons.Filled.ThumbUp,
    Icons.Filled.Build,
    Icons.Filled.ThumbUp,

    Icons.Filled.Check,
    Icons.Filled.Close,
    Icons.Filled.ThumbUp,
    Icons.Filled.Build,
    Icons.Filled.Delete,
    Icons.Filled.Home,
    Icons.Filled.Close,
    Icons.Filled.ThumbUp,
    Icons.Filled.Build,
    Icons.Filled.ThumbUp,

    Icons.Filled.Check,
    Icons.Filled.Close,
    Icons.Filled.ThumbUp,
    Icons.Filled.Build,
    Icons.Filled.Delete,
    Icons.Filled.Home,
    Icons.Filled.Close,
    Icons.Filled.ThumbUp,
    Icons.Filled.Build,
    Icons.Filled.ThumbUp,
)

@Composable
fun GridScreen() {
    GridView(columnCount = 3)

    BackButtonHandler {
        JetFundamentalsRouter.navigateTo(Screen.Navigation)
    }
}

@Composable
fun GridView(columnCount: Int) {
    val itemSize = items.size
    val rowCount = ceil(itemSize.toFloat() / columnCount).toInt()
    val gridItems = mutableListOf<List<GridIconResource>>()
    var position = 0

    for (i in 0 until rowCount) {
        val rowItem = mutableListOf<GridIconResource>()
        for (j in 0 until columnCount) {
            if (position.inc() <= itemSize) {
                rowItem.add(GridIconResource(items[position++], true))
            }
        }

        val itemsToFill = columnCount - rowItem.size
        for (j in 0 until itemsToFill) {
            rowItem.add(GridIconResource(Icons.Filled.Delete, false))
        }

        gridItems.add(rowItem)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = gridItems) { item ->
            RowItem(item)
        }
    }
}

@Composable
fun RowItem(rowItems: List<GridIconResource>) {
    Row {
        rowItems.forEach {
            with(this) {
                GridIcon(iconResource = it)
            }
        }
    }
}

@Composable
fun RowScope.GridIcon(iconResource: GridIconResource) {
    val color = if (iconResource.isVisible) colorResource(R.color.colorPrimary) else Color.Transparent

    Icon(
        imageVector = iconResource.imageVector,
        contentDescription = "grid icon",
        tint = color,
        modifier = Modifier
            .size(80.dp, 80.dp)
            .weight(1f)
    )
}

data class GridIconResource(
    val imageVector: ImageVector,
    val isVisible: Boolean
)
