package com.websarva.wings.android.movieapp.domain.model

data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String,
)
