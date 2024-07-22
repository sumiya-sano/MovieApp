package com.websarva.wings.android.movieapp.domain.entity

data class Comment(
    val commentBody: String?,
    val commentId: Int?,
    val movieId: Comparable<*>,
    val userId: Comparable<*>
)