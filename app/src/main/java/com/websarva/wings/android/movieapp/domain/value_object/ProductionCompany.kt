package com.websarva.wings.android.movieapp.domain.value_object


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    val id: Int?,
    @Json(name = "logo_path")
    val logoPath: String?,
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: String?
)