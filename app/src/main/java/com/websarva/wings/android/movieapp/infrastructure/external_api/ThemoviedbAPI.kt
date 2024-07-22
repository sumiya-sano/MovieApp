package com.websarva.wings.android.movieapp.infrastructure.external_api

import com.websarva.wings.android.movieapp.application.keyword_search_usecase.KeywordSearchMovieDto
import com.websarva.wings.android.movieapp.application.movie_detail_usecase.SearchMovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ThemoviedbAPI {

    @Headers("Authorization: Bearer ${TmdbApiKeystore.ACCESS_TOKEN}")
    @GET("/3/search/movie")
    suspend fun keywordSearchMovies(@Query("query") query: String, @Query("language") language: String): KeywordSearchMovieDto

    @Headers("Authorization: Bearer ${TmdbApiKeystore.ACCESS_TOKEN}")
    @GET("/3/movie/{movie_id}")
    suspend fun searchMovieDetail(@Path("movie_id") movieId: Int, @Query("language") language: String): SearchMovieDetailDto
}