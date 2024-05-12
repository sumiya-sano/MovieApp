package com.websarva.wings.android.movieapp.data.repository

import android.util.Log
import com.websarva.wings.android.movieapp.data.remote.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.data.remote.SearchMovieDetailDto
import com.websarva.wings.android.movieapp.data.remote.ThemoviedbAPI
import com.websarva.wings.android.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

//コンストラクタ・インジェクション
 class MovieRepositoryImpl @Inject constructor(
    private val api: ThemoviedbAPI,
) : MovieRepository {
    override suspend fun keywordSearchMovies(query: String): KeywordSearchMovieDto {
        try {
            val result = api.keywordSearchMovies(query)
            return result
        } catch (e: Exception) {
            throw e // 例外を再スローして上位のコードで処理できるようにする
        }
    }

    override suspend fun searchMovieDetail(movieId: Int): SearchMovieDetailDto {
        return api.searchMovieDetail(movieId)
    }
}