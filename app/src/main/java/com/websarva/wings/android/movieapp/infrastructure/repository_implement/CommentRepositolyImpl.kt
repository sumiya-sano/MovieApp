package com.websarva.wings.android.movieapp.infrastructure.repository_implement

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.internal_api.CommentsAPI
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val api: CommentsAPI,
) : CommentRepository{
    override suspend fun getComment(movieId: Int): GetCommentDto {
        try {
            return api.getSuccessComments(movieId)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun putComment(
        commentId: Int,
        commentBody: Map<String, String>
    ): ResMessage {
        val response = api.putSuccessComment(commentId, commentBody)

        if (response.isSuccessful){
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Error occurred: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun postComment(request: PostCommentRequest ): ResMessage {
        val response = api.postSuccessComment(request)

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Error occurred: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun deleteComment(commentId: Int): ResMessage {
        val response = api.deleteSuccessComment(commentId)

        if (response.isSuccessful){
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Error occurred: ${response.errorBody()?.string()}")
        }
    }
}