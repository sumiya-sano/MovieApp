package com.websarva.wings.android.movieapp.data.remote

import android.adservices.adid.AdId
import com.websarva.wings.android.movieapp.common.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ThemoviedbAPI {
    //キーワード検索
    //認証のためにAPIリードアクセストークンをHttpRequestHedderに含める
    @Headers("Authorization: Bearer ${Constants.ACCESS_TOKEN}")
    //通信方法(今回はGET)とエンドポイントを指定
    @GET("/3/search/movie")
    //引数でクエリを注入
    suspend fun keywordSearchMovies(@Query("query") query: String, @Query("language") language: String): KeywordSearchMovieDto

    //詳細検索
    @Headers("Authorization: Bearer ${Constants.ACCESS_TOKEN}")
    //通信方法(今回はGET)とエンドポイントを指定
    @GET("/3/movie/{movie_id}")
    //引数でクエリを注入
    suspend fun searchMovieDetail(@Path("movie_id") movieId: Int, @Query("language") language: String): SearchMovieDetailDto
}