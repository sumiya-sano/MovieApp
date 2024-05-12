package com.websarva.wings.android.movieapp.presentation.keyword_search_movie

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.websarva.wings.android.movieapp.domain.model.Movie
import com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components.MovieThumbnail

@Composable
fun KeywordSearchMoviesScreen(
    viewModel: KeywordSeachMovieViewModel = hiltViewModel(),
){
    val state = viewModel.state.value
    LazyColumn {
        items(state.movies) { movie ->
            MovieThumbnail(movie = movie, onClick = {})
        }
    }
}