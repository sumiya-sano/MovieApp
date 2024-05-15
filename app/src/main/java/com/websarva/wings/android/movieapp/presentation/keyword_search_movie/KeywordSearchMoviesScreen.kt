package com.websarva.wings.android.movieapp.presentation.keyword_search_movie

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.websarva.wings.android.movieapp.domain.model.Movie
import com.websarva.wings.android.movieapp.presentation.ScreenRoute
import com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components.MovieThumbnail
import com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components.SearchBar

@Composable
fun KeywordSearchMoviesScreen(
    navController: NavController,
    viewModel: KeywordSeachMovieViewModel = hiltViewModel(),
){
    val state = viewModel.state.value

    Scaffold (
        topBar = {
            SearchBar(
                searchText = viewModel.query,
                onSearchTextChanged = {viewModel.query = it},
                onDane = { viewModel.keywordSearchMovies()}
            )
        }
    ) {paddingValue ->
        Box(modifier = Modifier.fillMaxSize()){
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
                    LazyColumn(modifier = Modifier.padding(paddingValue)) {
                        items(state.movies) { movie ->
                            MovieThumbnail(
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