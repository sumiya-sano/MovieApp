package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.entity.Comment

data class GetCommentDto(
    val comments: List<CommentDto?>?
)

data class CommentDto(
    val comment_id: Int?,
    val user_id: Int?,
    val comment_body: String?,
    val movie_id: Int?
)

fun GetCommentDto.toComments(): List<Comment> {
    return comments?.mapNotNull {
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
