package com.websarva.wings.android.movieapp.presentation.keyword_search_movie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.websarva.wings.android.movieapp.common.Constants.IMAGE_URL
import com.websarva.wings.android.movieapp.domain.model.Movie
import com.websarva.wings.android.movieapp.presentation.components.CountLabel

@Composable
fun MovieThumbnail(
    movie: Movie,
    onClick: (Movie) -> Unit
){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .heightIn(min = 500.dp)
            .clickable { onClick(movie) },
        contentAlignment = Alignment.BottomCenter,
    ){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
        AsyncImage(
            model = IMAGE_URL+movie.posterPath,
            contentDescription = movie.overview,
            modifier = Modifier.fillMaxWidth(),
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
        ) {
            CountLabel(
                imageVector = Icons.Default.Star,
                count = movie.popularity?.toInt() ?: 0,
                iconTint = Color.White,
                color = Color.White,
            )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(10.dp)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                Text(
                    text = movie.title ?: "Unknown",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = movie.overview ?: "No overview",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

