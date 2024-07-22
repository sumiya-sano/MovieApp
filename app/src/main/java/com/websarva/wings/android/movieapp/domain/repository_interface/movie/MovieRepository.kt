package com.websarva.wings.android.movieapp.domain.repository_interface.movie

import com.websarva.wings.android.movieapp.application.keyword_search_usecase.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.application.movie_detail_usecase.SearchMovieDetailDto

interface MovieRepository {

    suspend fun keywordSearchMovies(query: String): KeywordSearchMovieDto

    suspend fun searchMovieDetail(movieId: Int): SearchMovieDetailDto
}