package com.websarva.wings.android.movieapp.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BelongsToCollection(
    @Json(name = "backdrop_path")
    val backdropPath: Any?,
    val id: Int?,
    val name: String?,
    @Json(name = "poster_path")
    val posterPath: String?
)