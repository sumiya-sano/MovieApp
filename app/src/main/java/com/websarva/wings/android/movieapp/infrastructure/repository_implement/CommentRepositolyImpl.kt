package com.websarva.wings.android.movieapp.infrastructure.repository_implement

import android.util.Log
import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.internal_api.CommentsAPI
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val api: CommentsAPI,
) : CommentRepository{
    override suspend fun getComment(): GetCommentDto {
        try {
            return api.getSuccessComments()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun putComment(commentId: Int, messageBody: Map<String, String>): ResMessage {
        Log.d("コメント", "commentId ${commentId}" )
        Log.d("コメント", "messageBody ${messageBody}" )
        val response = api.putSuccessComment(commentId, messageBody)

        if (response.isSuccessful){
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Error occurred: ${response.errorBody()?.string()}")
        }
    }
}