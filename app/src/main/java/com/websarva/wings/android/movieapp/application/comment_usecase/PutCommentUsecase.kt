package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PutCommentUsecase @Inject constructor(
    private val repository: CommentRepository
){
    operator fun invoke(
        commentId: Int, commentBody: Map<String, String>
    ): Flow<NetworkResponse<String>> = flow {
        try {
            emit(NetworkResponse.Loading())
            repository.putComment(commentId,commentBody)
            emit(NetworkResponse.Success("コメントを編集しました"))
        } catch (e: Exception){
            emit(NetworkResponse.Failure(e.message.toString()))
        }
    }
}