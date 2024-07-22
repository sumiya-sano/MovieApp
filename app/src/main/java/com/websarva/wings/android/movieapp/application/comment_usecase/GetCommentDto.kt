package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.entity.Comment

data class CommentDto(
    val comment_body: String?,
    val comment_id: Int?,
    val movie_id: String?,
    val update_date: String?,
    val user_id: String?
)

fun List<CommentDto>?.toComments(): List<Comment> {
    return this?.mapNotNull {
        it?.let { dto ->
            Comment(
                commentId = dto.comment_id ?: 0,
                userId = dto.user_id ?: 0,
                commentBody = dto.comment_body,
                movieId = dto.movie_id ?: 0
            )
        }
    } ?: emptyList()
}
