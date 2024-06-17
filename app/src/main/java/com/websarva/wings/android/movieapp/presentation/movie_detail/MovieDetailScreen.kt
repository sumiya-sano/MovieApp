package com.websarva.wings.android.movieapp.presentation.movie_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.websarva.wings.android.movieapp.domain.entity.Comment
import com.websarva.wings.android.movieapp.infrastructure.external_api.TmdbApiKeystore
import com.websarva.wings.android.movieapp.domain.entity.MovieDetail
import com.websarva.wings.android.movieapp.presentation.comment.CommentViewModel
import com.websarva.wings.android.movieapp.presentation.comment.CommentsComponent
import com.websarva.wings.android.movieapp.presentation.components.CountLabel

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
){
    val movieDetailState by movieDetailViewModel.state

        Box {
            when {
                movieDetailState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !movieDetailState.error.isNullOrBlank() -> {
                    Text(
                        text = movieDetailState.error!!,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    Column {
                        movieDetailState.movieDetail?.let { movieDetail ->
                            MovieDetailContent(movieDetail = movieDetail)
                        }
                    }
                }
            }
        }
}

@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail,
    commentViewModel: CommentViewModel = hiltViewModel(),
    ) {
    val commentState by commentViewModel.state
    Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box (modifier = Modifier.heightIn(min = 200.dp)) {
            var isLoadingImage by remember { mutableStateOf(true) }
            if (isLoadingImage) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            AsyncImage(
                model = TmdbApiKeystore.IMAGE_URL +movieDetail.posterPath,
                contentDescription = movieDetail.overview,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 5,
                            bottomEndPercent = 5,
                        )
                    ),
                onSuccess = { isLoadingImage = false },
            )
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = movieDetail.title ?: "No title",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = movieDetail.overview ?: "No overview",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            CountLabel(
                imageVector = Icons.Default.Star,
                count = movieDetail.popularity?.toInt() ?: 0,
                iconTint = Color.Magenta,
            )
            Spacer(modifier = Modifier.height(20.dp))
            val genreNames = movieDetail.genres?.map { it?.name }
            val genres = genreNames?.joinToString(", ")
            Text(text = "ジャンル: ${genres}")
            val productionCompaniesNames = movieDetail.productionCompanies?.map { it?.name }
            val productionCompanies = productionCompaniesNames?.joinToString(", ")
            Text(text = "制作会社: ${productionCompanies}")
            if (commentState.comments.size > 0) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
            CommentsList(comments = commentState.comments)
        }
    }
}

@Composable
fun CommentsList(comments: List<Comment>) {
    Column(modifier = Modifier.padding(5.dp)) {
        comments.forEach { comment ->
            CommentsComponent(comment = comment)
            Spacer(modifier = Modifier.height(8.dp)) // 各コメントの間にスペースを追加
        }
    }
}