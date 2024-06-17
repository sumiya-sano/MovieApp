package com.websarva.wings.android.movieapp.infrastructure.internal_api

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CommentsAPI {
    //コメントMock
    //successパターンのGET
    @GET("/internalapi/v1/comments/fetch/3258")
    suspend fun getSuccessComments() : GetCommentDto

    //errorパターンのGET
    @GET("/internalapi/v1/comments/fetch/9999")
    suspend fun getErrorComments() : GetCommentDto

    //successパターンのPOST
//    @Headers("Content-Type: application/json")
//    @POST("http://localhost:8080/internalapi/v1/comments")


    //errorパターンのPOST

    //successパターンのPUT

    //errorパターンのPUT

    //successパターンのDELETE

    //errorパターンのDELETE
}