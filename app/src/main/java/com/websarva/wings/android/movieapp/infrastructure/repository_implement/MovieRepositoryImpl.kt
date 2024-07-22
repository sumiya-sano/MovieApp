package com.websarva.wings.android.movieapp.infrastructure.repository_implement

import com.websarva.wings.android.movieapp.application.keyword_search_usecase.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.application.movie_detail_usecase.SearchMovieDetailDto
import com.websarva.wings.android.movieapp.infrastructure.external_api.ThemoviedbAPI
import com.websarva.wings.android.movieapp.domain.repository_interface.movie.MovieRepository
import javax.inject.Inject

 class MovieRepositoryImpl @Inject constructor(
    private val api: ThemoviedbAPI,
) : MovieRepository {
    override suspend fun keywordSearchMovies(query: String): KeywordSearchMovieDto {
        try {
            return api.keywordSearchMovies(query, language = "ja")
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchMovieDetail(movieId: Int): SearchMovieDetailDto {
        return api.searchMovieDetail(movieId, language = "ja")
    }
}