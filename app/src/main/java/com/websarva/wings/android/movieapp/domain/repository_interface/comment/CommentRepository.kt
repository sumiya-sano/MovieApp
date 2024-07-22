package com.websarva.wings.android.movieapp.domain.repository_interface.comment


import com.websarva.wings.android.movieapp.application.comment_usecase.CommentDto
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest

interface CommentRepository {
    suspend fun getComment(movieId: Int): List<CommentDto>
    suspend fun putComment(commentId: Int, commentBody: Map<String, String>)
    suspend fun postComment(request: PostCommentRequest)
    suspend fun deleteComment(commentId: Int)
}