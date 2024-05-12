package com.websarva.wings.android.movieapp.domain.model

import com.websarva.wings.android.movieapp.data.remote.Genre
import com.websarva.wings.android.movieapp.data.remote.ProductionCompany
import com.websarva.wings.android.movieapp.data.remote.ProductionCountry
import com.websarva.wings.android.movieapp.data.remote.SpokenLanguage
import kotlin.reflect.jvm.internal.impl.protobuf.LazyStringArrayList

data class MovieDetail(
    val movieId: Int?,
    val title: String,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val genres:  List<Genre?>?,
    val productionCompanies: List<ProductionCompany?>?,
)
