package com.websarva.wings.android.movieapp.domain.entity



data class PostCommentRequest(
    val user_id: Int,
    val movie_id: Int,
    val message_body: String
)
