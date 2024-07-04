package com.websarva.wings.android.movieapp.presentation.comment.delete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.movieapp.application.comment_usecase.deleteCommentUsecase
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteCommentViewModel @Inject constructor(
    private val deleteCommentUsecase: deleteCommentUsecase,
) :ViewModel() {
    private val _deleteCommentState = MutableStateFlow<NetworkResponse<ResMessage>>(NetworkResponse.Loading())
    val deleteCommentState: StateFlow<NetworkResponse<ResMessage>> = _deleteCommentState

    fun deleteComment(commentId: Int) {
        viewModelScope.launch {
            deleteCommentUsecase(commentId).collect {response ->
                _deleteCommentState.value = response
            }
        }
    }
}