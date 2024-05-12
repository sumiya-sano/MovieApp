package com.websarva.wings.android.movieapp.domain.use_case

import com.websarva.wings.android.movieapp.common.NetworkResponse
import com.websarva.wings.android.movieapp.data.remote.toMovies
import com.websarva.wings.android.movieapp.domain.model.Movie
import com.websarva.wings.android.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KeywordSearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    //Strong型の検索ワードqueryを引数に、検索結果をListで返すメソッド
    operator fun invoke(query: String): Flow<NetworkResponse<List<Movie>>> = flow {
        //FlowでNetworkResponseの状態を監視し、emit()で状態を送る
        try {
            //最初は通信中なのでLoading
            emit(NetworkResponse.Loading<List<Movie>>())
            val movies = repository.keywordSearchMovies(query).toMovies()
            //次に通信成功
            emit(NetworkResponse.Success<List<Movie>>(movies))
        } catch (e: Exception) {
            //通信エラーをキャッチしたらFlowの値を失敗にして引数でエラー文を渡す
            emit(NetworkResponse.Failure<List<Movie>>(e.message.toString()))
        }
    }
}