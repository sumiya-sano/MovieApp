package com.websarva.wings.android.movieapp.presentation.comment

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentUsecase
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val getCommentUsecase: GetCommentUsecase,
) :ViewModel(){
    private val _state = mutableStateOf(CommentState())
    val state: State<CommentState> = _state

    init {
        getComments()
    }
    fun getComments(){
        getCommentUsecase().onEach {result ->
            when(result){
                is NetworkResponse.Success -> {
                    _state.value = CommentState(
                        isLoading = false,
                        comments = result.data ?: emptyList(),
                    )
                    Log.d("コメント", "成功")
                }
                is NetworkResponse.Failure -> {
                    _state.value = CommentState(error = result.error)
                    Log.d("コメント", "失敗" + result.error)
                }
                is NetworkResponse.Loading -> {
                    _state.value = CommentState(isLoading = true)
                }
            }
            Log.d("コメント", _state.value.comments.toString())
        }.launchIn(viewModelScope)
    }
}