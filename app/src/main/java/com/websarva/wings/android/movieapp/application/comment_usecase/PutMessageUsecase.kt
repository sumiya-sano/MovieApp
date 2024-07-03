package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PutMessageUsecase @Inject constructor(
    private val repository: CommentRepository
){
    operator fun invoke(commentId: Int, messageBody: Map<String, String>): Flow<NetworkResponse<ResMessage>> = flow {
        try {
            emit(NetworkResponse.Loading<ResMessage>())
            val message = repository.putComment(commentId,messageBody)
            emit(NetworkResponse.Success<ResMessage>(message))
        } catch (e: Exception){
            emit(NetworkResponse.Failure<ResMessage>(e.message.toString()))
        }
    }
}