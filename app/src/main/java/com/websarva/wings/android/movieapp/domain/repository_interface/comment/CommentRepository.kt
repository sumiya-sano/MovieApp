package com.websarva.wings.android.movieapp.domain.repository_interface.comment

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import com.websarva.wings.android.movieapp.domain.entity.ResMessage

interface CommentRepository {

    //GET
    suspend fun getComment(movieId: Int): GetCommentDto
    //PUT
    suspend fun putComment(commentId: Int, messageBody: Map<String, String>): ResMessage
    //POST
    suspend fun postComment(request: PostCommentRequest): ResMessage
    //DELETE
    suspend fun deleteComment(commentId: Int): ResMessage
}