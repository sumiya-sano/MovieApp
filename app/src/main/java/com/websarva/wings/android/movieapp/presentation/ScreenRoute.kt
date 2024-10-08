package com.websarva.wings.android.movieapp.presentation

sealed class ScreenRoute(val route: String) {
    object SearchMoviesScreen: ScreenRoute("search_movies_screen")
    object MovieDetailScreen: ScreenRoute("movie_detail_screen")
}