package com.websarva.wings.android.movieapp.domain.repository_interface

import com.websarva.wings.android.movieapp.application.keyword_search_usecase.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.application.movie_detail_usecase.SearchMovieDetailDto

interface MovieRepository {

    //キーワード検索DTO。Implから参照することでドメインへ依存する形にできる。
    suspend fun keywordSearchMovies(query: String): KeywordSearchMovieDto

    //詳細検索DTO。Implから参照することでドメインへ依存する形にできる。
    suspend fun searchMovieDetail(movieId: Int): SearchMovieDetailDto
}