package com.websarva.wings.android.movieapp.presentation.keyword_search_movie

import com.websarva.wings.android.movieapp.common.NetworkResponse
import com.websarva.wings.android.movieapp.domain.model.Movie

data class KeywordSearchMovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null,
)
