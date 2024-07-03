package com.websarva.wings.android.movieapp.application.comment_usecase

import android.util.Log
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostMessageUsecase @Inject constructor(
    private val repository: CommentRepository
) {
    operator fun invoke(
        userId: Int, movieId: Int, messageBody: String,
    ): Flow<NetworkResponse<ResMessage>> = flow {
        try {
            emit(NetworkResponse.Loading<ResMessage>())

            val request = PostCommentRequest(userId, movieId, messageBody)
            val message = repository.postComment(request)
            emit(NetworkResponse.Success<ResMessage>(message))
            Log.d("post", "usecaseまで成功")
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<ResMessage>(e.message.toString()))
            Log.d("post", "usecaseでException" + e.message.toString())
        }
    }
}