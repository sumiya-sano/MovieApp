package com.websarva.wings.android.movieapp.domain.entity

data class Movie(
    val movieId: Int,
    val title: String,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String,
)
