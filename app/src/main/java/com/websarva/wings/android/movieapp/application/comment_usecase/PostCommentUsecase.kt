package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostCommentUsecase @Inject constructor(
    private val repository: CommentRepository
) {
    operator fun invoke(
        userId: Int, movieId: Int, commentBody: String,
    ): Flow<NetworkResponse<String>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val request = PostCommentRequest( userId, movieId, commentBody)
            repository.postComment(request)
            emit(NetworkResponse.Success("コメントを投稿しました！"))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure(e.message.toString()))
        }
    }
}