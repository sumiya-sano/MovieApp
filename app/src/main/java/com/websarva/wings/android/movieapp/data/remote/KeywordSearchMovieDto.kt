package com.websarva.wings.android.movieapp.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.websarva.wings.android.movieapp.domain.model.Movie

@JsonClass(generateAdapter = true)
data class KeywordSearchMovieDto(
    val results: List<Result?>?,
    val page: Int?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)

//Dto->modelに変換
fun KeywordSearchMovieDto.toMovies(): List<Movie> {
    return results?.mapNotNull {
        it?.let { result ->
            Movie(
                movieId = result.id ?: 0,
                title = result.title.orEmpty(),
                overview = result.overview.orEmpty(),
                popularity = result.popularity ?: 0.0,
                posterPath = result.posterPath.orEmpty(),
            )
        }
    } ?: emptyList()
}