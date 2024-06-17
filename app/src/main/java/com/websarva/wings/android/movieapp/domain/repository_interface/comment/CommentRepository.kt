package com.websarva.wings.android.movieapp.domain.repository_interface.comment

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto

interface CommentRepository {

    //GET
    suspend fun getComment(): GetCommentDto
    //POST
    //PUT
    //DELETE
}