package com.websarva.wings.android.movieapp.domain.use_case

import com.websarva.wings.android.movieapp.common.NetworkResponse
import com.websarva.wings.android.movieapp.data.remote.toMovieDetail
import com.websarva.wings.android.movieapp.domain.model.MovieDetail
import com.websarva.wings.android.movieapp.domain.repository.MovieRepository
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