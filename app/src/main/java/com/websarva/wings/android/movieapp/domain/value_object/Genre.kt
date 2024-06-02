package com.websarva.wings.android.movieapp.domain.value_object


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Int?,
    val name: String?
)