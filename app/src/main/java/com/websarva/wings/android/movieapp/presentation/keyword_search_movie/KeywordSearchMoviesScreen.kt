package com.websarva.wings.android.movieapp.presentation.keyword_search_movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.websarva.wings.android.movieapp.R
import com.websarva.wings.android.movieapp.presentation.ScreenRoute
import com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components.MovieThumbnailCard
import com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components.SearchBar

@Composable
fun KeywordSearchMoviesScreen(
    navController: NavController,
    viewModel: KeywordSeachMovieViewModel = hiltViewModel(),
){
    val state = viewModel.state.value
    val gridViewState = remember { mutableStateOf(true) }

    Scaffold (
        topBar = {
            Row {
                SearchBar(
                    searchText = viewModel.query,
                    onSearchTextChanged = {viewModel.query = it},
                    onDane = { viewModel.keywordSearchMovies()}
                )
                IconButton(
                    onClick = {
                              if (gridViewState.value){
                                  gridViewState.value = false
                              }else{
                                  gridViewState.value = true
                              }
                              },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.view_cozy_24px),
                        contentDescription = "表示切り替え",
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    ) {paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ){
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !state.error.isNullOrBlank() -> {
                    Text(
                        text = state.error,
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                else -> {
                    if (gridViewState.value){
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 160.dp),
                            modifier = Modifier.padding(paddingValue)
                        ) {
                            items(state.movies) { movie ->
                                MovieThumbnailCard(
                                    movie = movie,
                                    onClick = {navController.navigate(ScreenRoute.MovieDetailScreen.route + "/${movie.movieId}") }
                                )
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.padding(paddingValue)
                        ) {
                            items(state.movies) { movie ->
                                MovieThumbnailCard(
                                    movie = movie,
                                    onClick = {navController.navigate(ScreenRoute.MovieDetailScreen.route + "/${movie.movieId}") }
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}