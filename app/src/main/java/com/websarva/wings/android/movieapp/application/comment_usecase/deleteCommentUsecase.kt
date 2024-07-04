package com.websarva.wings.android.movieapp.application.comment_usecase

import android.util.Log
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class deleteCommentUsecase @Inject constructor(
    private val repository: CommentRepository,
) {
    operator fun invoke(commentId: Int): Flow<NetworkResponse<ResMessage>> = flow {
        try {
            emit(NetworkResponse.Loading<ResMessage>())
            val message = repository.deleteComment(commentId)
            emit(NetworkResponse.Success<ResMessage>(message))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<ResMessage>(e.message.toString()))
            Log.d("delete","usecase" + e.message.toString())
        }
    }
}