package com.websarva.wings.android.movieapp.infrastructure.internal_api

import com.websarva.wings.android.movieapp.application.comment_usecase.GetCommentDto
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import com.websarva.wings.android.movieapp.domain.entity.ResMessage
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

//コメントMock
interface CommentsAPI {
    //successパターンのGET
    @GET("/internalapi/v1/comments/fetch/{movie_id}")
    suspend fun getSuccessComments(
        @Path("movie_id") movieId: Int,
    ): GetCommentDto

    //PUT
    @PUT("/internalapi/v1/comments/{comment_id}")
    suspend fun putSuccessComment(
        @Path("comment_id") commentId: Int,
        @Body commentBody: Map<String, String>
    ): Response<ResMessage>

    //POST
    @POST("/internalapi/v1/comments")
    suspend fun postSuccessComment(@Body request: PostCommentRequest): Response<ResMessage>

    //DELETE
    @DELETE("/internalapi/v1/comments/{comment_id}")
    suspend fun deleteSuccessComment(
        @Path("comment_id") commentId: Int,
    ): Response<ResMessage>
}