package com.websarva.wings.android.movieapp.presentation.comment.Put

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.movieapp.application.comment_usecase.PutCommentUsecase
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PutCommentViewModel @Inject constructor(
    private val putMessageUsecase: PutCommentUsecase,
) : ViewModel() {

    private val _putCommentState = MutableStateFlow<NetworkResponse<String>>(NetworkResponse.Loading())
    val putCommentState: StateFlow<NetworkResponse<String>> = _putCommentState

    fun putComment(commentId: Int, commentBody: String) {
        val requestBody = mapOf(
            "comment_body" to commentBody
        )

        viewModelScope.launch {
            putMessageUsecase(commentId, requestBody).collect { response ->
                _putCommentState.value = response
            }
        }
    }

    fun resetPutCommentState(){
        _putCommentState.value = NetworkResponse.Loading()
    }
}