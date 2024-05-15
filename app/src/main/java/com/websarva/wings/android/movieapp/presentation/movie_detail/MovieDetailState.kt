package com.websarva.wings.android.movieapp.presentation.movie_detail

import com.websarva.wings.android.movieapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String? = null,
)
