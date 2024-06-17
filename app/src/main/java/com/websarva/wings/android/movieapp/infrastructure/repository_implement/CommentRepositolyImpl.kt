package com.websarva.wings.android.movieapp.infrastructure.repository_implement

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
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
}