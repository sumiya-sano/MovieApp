package com.websarva.wings.android.movieapp.presentation.movie_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.websarva.wings.android.movieapp.common.NetworkResponse
import com.websarva.wings.android.movieapp.domain.use_case.SearchMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val searchMovieDetailUseCase: SearchMovieDetailUseCase,
    savedStateHandle: SavedStateHandle,
) :ViewModel(){
    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        //navigationのrouteのパスパラメータで渡されたmovieIdを受け取る初期処理
        //movieIdを受け取れたら、Int型にキャストしてからそれを引数にgetMovieDetailをコール
        savedStateHandle.get<String>("movieId")?.toIntOrNull()?.let { movieId ->
            getMovieDetail(movieId)
        }
    }

     private fun getMovieDetail(movieId: Int){
         //SearchMovieDetailUseCaseのinvoke()をコールバックし、TMDbAPIから映画詳細を取得
         searchMovieDetailUseCase(movieId).onEach {result ->
             when (result) {
                 is NetworkResponse.Success -> {
                     _state.value = MovieDetailState(movieDetail = result.data)
                 }

                 is NetworkResponse.Failure -> {
                     _state.value = MovieDetailState(error = result.error)
                 }

                 is NetworkResponse.Loading -> {
                     _state.value = MovieDetailState(isLoading = true)
                 }
             }
         }.launchIn(viewModelScope)
     }
}