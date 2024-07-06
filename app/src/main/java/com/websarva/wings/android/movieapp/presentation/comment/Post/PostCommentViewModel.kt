package com.websarva.wings.android.movieapp.presentation.comment.Post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.movieapp.application.comment_usecase.PostCommentUsecase
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostCommentViewModel @Inject constructor(
    private val postCommentUsecase: PostCommentUsecase,
) : ViewModel(){

    private val _postCommentState = MutableStateFlow<NetworkResponse<ResMessage>>(NetworkResponse.Loading())
    val postCommentState: StateFlow<NetworkResponse<ResMessage>> = _postCommentState

    fun postComment(userId: Int, movieId: Int, commentBody: String) {
        viewModelScope.launch {
            postCommentUsecase(userId, movieId, commentBody).collect {response ->
                _postCommentState.value = response
            }
        }
    }
}