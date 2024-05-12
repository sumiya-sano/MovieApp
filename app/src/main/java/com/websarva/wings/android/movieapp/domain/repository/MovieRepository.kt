package com.websarva.wings.android.movieapp.domain.repository

import com.websarva.wings.android.movieapp.data.remote.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.data.remote.SearchMovieDetailDto

interface MovieRepository {

    //キーワード検索DTO。Implから参照することでドメインへ依存する形にできる。
    suspend fun keywordSearchMovies(query: String): KeywordSearchMovieDto

    //詳細検索DTO。Implから参照することでドメインへ依存する形にできる。
    suspend fun searchMovieDetail(movieId: Int): SearchMovieDetailDto
}