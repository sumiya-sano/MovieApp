package com.websarva.wings.android.movieapp.presentation.keyword_search_movie

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.movieapp.common.NetworkResponse
import com.websarva.wings.android.movieapp.domain.use_case.KeywordSearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class KeywordSeachMovieViewModel @Inject constructor(
    private val keywordSearchMovieUseCase: KeywordSearchMovieUseCase,
) :ViewModel() {
    private val _state = mutableStateOf(KeywordSearchMovieState())
    val state: State<KeywordSearchMovieState> = _state

    var query by mutableStateOf("")

    init {
        keywordSearchMovies()
    }

    fun keywordSearchMovies(){
        keywordSearchMovieUseCase(query).onEach { result ->
            when(result){
                is NetworkResponse.Success -> {
                    _state.value = KeywordSearchMovieState(
                        isLoading = false,
                        movies = result.data ?: emptyList(),
                    )
                }
                is NetworkResponse.Failure -> {
                    _state.value = KeywordSearchMovieState(error = result.error)
                }
                is NetworkResponse.Loading ->{
                    _state.value = KeywordSearchMovieState(isLoading = true)
                }

            }
        }.launchIn(viewModelScope)
    }
}