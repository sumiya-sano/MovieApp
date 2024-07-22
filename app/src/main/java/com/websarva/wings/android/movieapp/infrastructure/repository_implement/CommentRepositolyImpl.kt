package com.websarva.wings.android.movieapp.infrastructure.repository_implement

import com.websarva.wings.android.movieapp.application.comment_usecase.CommentDto
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.internal_api.CommentsAPI
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val api: CommentsAPI,
) : CommentRepository{
    override suspend fun getComment(movieId: Int): List<CommentDto> {
        try {
            return api.getComments(movieId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun putComment(
        commentId: Int,
        commentBody: Map<String, String>
    ){
        try {
            api.putComment(commentId, commentBody)
        } catch (e: Exception) {
            throw Exception("put失敗: ${e.message.toString()}")
        }
    }

    override suspend fun postComment(request: PostCommentRequest ) {
        try {
            api.postComment(request)
        } catch (e: Exception) {
            throw Exception("post失敗: ${e.message.toString()}")
        }
    }

    override suspend fun deleteComment(commentId: Int) {
        try {
            api.deleteComment(commentId)
        } catch (e:Exception) {
            throw Exception("delete失敗: ${e.message.toString()}")
        }
    }
}