package com.websarva.wings.android.movieapp.domain.entity

import com.websarva.wings.android.movieapp.domain.value_object.Genre
import com.websarva.wings.android.movieapp.domain.value_object.ProductionCompany

data class MovieDetail(
    val movieId: Int,
    val title: String,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val genres:  List<Genre?>?,
    val productionCompanies: List<ProductionCompany?>?,
)
