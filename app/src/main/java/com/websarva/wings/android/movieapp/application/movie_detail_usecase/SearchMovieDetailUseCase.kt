package com.websarva.wings.android.movieapp.application.movie_detail_usecase

import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import com.websarva.wings.android.movieapp.domain.entity.MovieDetail
import com.websarva.wings.android.movieapp.domain.repository_interface.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    //movieIdを引数に、詳細情報のListを返すメソッド
    operator fun invoke(movieId: Int): Flow<NetworkResponse<MovieDetail>> = flow {
        try {
            emit(NetworkResponse.Loading<MovieDetail>())
            val movieDetail = repository.searchMovieDetail(movieId).toMovieDetail()
            emit(NetworkResponse.Success<MovieDetail>(movieDetail))
        }catch (e: Exception){
            emit(NetworkResponse.Failure<MovieDetail>(e.message.toString()))
        }
    }
}