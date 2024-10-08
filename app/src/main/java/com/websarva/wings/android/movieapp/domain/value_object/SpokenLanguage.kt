package com.websarva.wings.android.movieapp.domain.value_object


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String?,
    @Json(name = "iso_639_1")
    val iso6391: String?,
    val name: String?
)