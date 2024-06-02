package com.websarva.wings.android.movieapp.presentation.keyword_search_movie

import com.websarva.wings.android.movieapp.domain.entity.Movie

data class KeywordSearchMovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
)
