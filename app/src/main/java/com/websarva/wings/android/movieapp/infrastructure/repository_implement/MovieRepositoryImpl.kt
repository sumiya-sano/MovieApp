package com.websarva.wings.android.movieapp.infrastructure.repository_implement

import com.websarva.wings.android.movieapp.application.keyword_search_usecase.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.application.movie_detail_usecase.SearchMovieDetailDto
import com.websarva.wings.android.movieapp.infrastructure.external_api.ThemoviedbAPI
import com.websarva.wings.android.movieapp.domain.repository_interface.MovieRepository
import javax.inject.Inject

//コンストラクタ・インジェクション
 class MovieRepositoryImpl @Inject constructor(
    private val api: ThemoviedbAPI,
) : MovieRepository {
    override suspend fun keywordSearchMovies(query: String): KeywordSearchMovieDto {
        try {
            val result = api.keywordSearchMovies(query, language = "ja")
            return result
        } catch (e: Exception) {
            throw e // 例外を再スローして上位のコードで処理できるようにする
        }
    }

    override suspend fun searchMovieDetail(movieId: Int): SearchMovieDetailDto {
        return api.searchMovieDetail(movieId, language = "ja")
    }
}