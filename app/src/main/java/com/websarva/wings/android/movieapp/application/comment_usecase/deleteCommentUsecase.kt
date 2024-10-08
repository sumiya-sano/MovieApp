package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class deleteCommentUsecase @Inject constructor(
    private val repository: CommentRepository,
) {
    operator fun invoke(commentId: Int): Flow<NetworkResponse<String>> = flow {
        try {
            emit(NetworkResponse.Loading())
            repository.deleteComment(commentId)
            emit(NetworkResponse.Success("コメントを削除しました！"))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure(e.message.toString()))
        }
    }
}