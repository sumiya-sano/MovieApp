package com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.websarva.wings.android.movieapp.infrastructure.external_api.TmdbApiKeystore.IMAGE_URL
import com.websarva.wings.android.movieapp.domain.entity.Movie
import com.websarva.wings.android.movieapp.presentation.components.CountLabel

@Composable
fun MovieThumbnailCard(
    movie: Movie,
    onClick: (Movie) -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .clickable { onClick(movie) }
            .padding(5.dp),
    ) {
        Column {
            AsyncImage(
                model = IMAGE_URL+movie.posterPath,
                contentDescription = movie.overview,
                modifier = Modifier.fillMaxWidth(),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(10.dp)
            ) {
                Text(
                    text = movie.title,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = movie.overview ?: "No Overview",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
                CountLabel(
                    imageVector = Icons.Default.Star,
                    count = movie.popularity?.toInt() ?: 0,
                    iconTint = Color.White,
                    color = Color.White,
                )
            }
        }
    }
}