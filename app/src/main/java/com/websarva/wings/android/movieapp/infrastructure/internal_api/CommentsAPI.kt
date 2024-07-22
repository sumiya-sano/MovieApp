package com.websarva.wings.android.movieapp.infrastructure.internal_api

import com.websarva.wings.android.movieapp.application.comment_usecase.CommentDto
import com.websarva.wings.android.movieapp.domain.entity.PostCommentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface CommentsAPI {
    @GET("/internalapi/v1/comments/fetch/{movie_id}")
    suspend fun getComments(
        @Path("movie_id") movieId: Int,
    ): List<CommentDto>

    @PUT("/internalapi/v1/comments/{comment_id}")
    suspend fun putComment(
        @Path("comment_id") commentId: Int,
        @Body commentBody: Map<String, String>
    ): Response<Unit>

    @POST("/internalapi/v1/comments")
    suspend fun postComment(@Body request: PostCommentRequest): Response<Unit>

    @DELETE("/internalapi/v1/comments/{comment_id}")
    suspend fun deleteComment(
        @Path("comment_id") commentId: Int,
    ): Response<Unit>
}