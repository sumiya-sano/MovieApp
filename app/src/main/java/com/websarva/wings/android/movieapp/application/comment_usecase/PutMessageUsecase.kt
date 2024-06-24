package com.websarva.wings.android.movieapp.application.comment_usecase

import android.util.Log
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
        Log.d("コメント","PUT処理入る")
        try {
            Log.d("コメント","try入る")
            emit(NetworkResponse.Loading<ResMessage>())
            Log.d("コメント","emitLoading完了->putComment入る")
            val message = repository.putComment(commentId,messageBody)
            Log.d("コメント","putComment完了")
            Log.d("コメント", message.message)
            emit(NetworkResponse.Success<ResMessage>(message))
        } catch (e: Exception){
            emit(NetworkResponse.Failure<ResMessage>(e.message.toString()))
            Log.d("コメント","PUT失敗")
            Log.d("コメント", e.message.toString())
        }
    }
}