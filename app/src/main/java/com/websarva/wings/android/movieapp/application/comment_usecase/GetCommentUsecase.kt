package com.websarva.wings.android.movieapp.application.comment_usecase

import com.websarva.wings.android.movieapp.domain.entity.Comment
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentUsecase @Inject constructor(
    private val repository: CommentRepository,
) {
    operator fun invoke(movieId: Int): Flow<NetworkResponse<List<Comment>>> = flow {
        try {
            emit(NetworkResponse.Loading<List<Comment>>())
            val comments = repository.getComment(movieId).toComments()
            emit(NetworkResponse.Success<List<Comment>>(comments))
        }catch (e: Exception){
            emit(NetworkResponse.Failure<List<Comment>>(e.message.toString()))
        }
    }
}