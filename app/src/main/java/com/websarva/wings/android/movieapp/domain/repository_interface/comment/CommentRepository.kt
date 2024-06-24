package com.websarva.wings.android.movieapp.domain.repository_interface.comment

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
import com.websarva.wings.android.movieapp.domain.entity.ResMessage

interface CommentRepository {

    //GET
    suspend fun getComment(): GetCommentDto
    //PUT
    suspend fun putComment(commentId: Int, messageBody: Map<String, String>): ResMessage
    //POST
    //DELETE
}