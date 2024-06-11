package com.example.ph44766_assignment.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ph44766_assignment.R
import com.example.ph44766_assignment.model.Favorites
import com.example.ph44766_assignment.model.favoriteItem


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun FavoriteScreen(
    navControl: NavHostController,
) {
    var filteredFavorites by remember { mutableStateOf(favoriteItem) }
    val favoritesList = remember { mutableStateListOf(*favoriteItem.toTypedArray()) }

    fun removeItem(favorite: Favorites) {
        favoritesList.remove(favorite)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "FAVORITE",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 25.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFF303030),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            if (filteredFavorites.isEmpty()) {
                Text(
                    text = "No favorite items",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            } else {
                FavoriteList(navControl = navControl, filteredFavorites, ::removeItem)
            }
        }
    }


}


@Composable
fun FavoriteList(
    navControl: NavHostController,
    filteredFavorites: List<Favorites>,
    removeItem: (Favorites) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 45.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(filteredFavorites.size) { favorite ->
            FavoriteItem(
                navControl = navControl,
                favorites = filteredFavorites[favorite],
                removeItem = removeItem
            )
        }
    }
}


@Composable
fun FavoriteItem(
    navControl: NavHostController,
    favorites: Favorites,
    removeItem: (Favorites) -> Unit
) {
    val context = LocalContext.current
    var listStateFavorites = remember {
        mutableStateListOf<Favorites>()
    }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .size(width = 400.dp, height = 130.dp)
            .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xfff1f1f1))
    ) {
        Row(
            modifier = Modifier
                .size(width = 380.dp, height = 130.dp)
        ) {
            AsyncImage(
                model = favorites.pic,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(15)),
                contentDescription = null,
                contentScale = ContentScale.Crop,

                )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp, horizontal = 10.dp)
            ) {
                Text(
                    text = favorites.title,
                    color = Color(0xff606060),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${favorites.price}",
                    color = Color(0xff303030),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
            }
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier.padding(end = 10.dp, top = 10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            removeItem(favorites)
                            Toast
                                .makeText(context, "Removed from favorites", Toast.LENGTH_SHORT)
                                .show()
                        },
                    contentDescription = null,
                    painter = painterResource(id = R.drawable.cancel),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun FavoriteScreenPreview() {
    FavoriteScreen(
        navControl = rememberNavController(),
    )
}